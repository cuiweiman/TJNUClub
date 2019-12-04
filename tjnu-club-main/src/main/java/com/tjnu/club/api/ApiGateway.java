package com.tjnu.club.api;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tjnu.club.component.ApiMetaInfoComponent;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.ApiMetaInfo;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.utils.ApplicationContextUtil;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 15:58
 * @Description: 网关接口
 */
@Slf4j
@RestController
@RequestMapping("/tjnu/club")
public class ApiGateway {

    private final static String REQUEST = "request";
    private final static String PARAMS = "params";

    private final static String REQUEST_APINAME = "apiName";
    private final static String REQUEST_TOKEN = "TJNUToken";

    private final static String TJNU_CLUB_OBJECT = "com.tjnu.club.vo";

    @Resource
    private ApiMetaInfoComponent apiMetaInfoComponent;

    @Resource
    private RedisDao redisDao;

    @PostMapping("/api")
    public Object api(@RequestBody Object payload, HttpServletRequest request) {
        try {
            if (payload instanceof Map) {
                return $invoke((Map) payload);
            } else { // 暂无 List 类型 及 Map 以外的类型
                return new ResultVO<>(TJNUConstants.UNSUPPORTED_REQUEST_BODY);
            }
        } catch (InvocationTargetException e) { //从反射异常中，获取到原方法异常，并返回。
            Object exceptionJson = JSON.toJSON(e.getTargetException());
            Map<String, Object> targetException = (Map<String, Object>) exceptionJson;
            Integer code = (Integer) targetException.get("code");
            String msg = (String) targetException.get("msg");
            log.error(msg,e);
            return new ResultVO<>(new TJNUException(code, msg));
        } catch (Exception e) {
            String errorTip = "[ " + request.getRemoteAddr() + " ]" + " requestBody: " + JSON.toJSONString(payload) + " exception: " + e.getMessage();
            log.error(errorTip, e);
            return new ResultVO<>(TJNUConstants.BAD_GATEWAY);
        }
    }


    private Object $invoke(final Map playloadParams) throws Exception {
        Map request = (Map) playloadParams.get(REQUEST);
        Map reqParams = (Map) playloadParams.get(PARAMS);

        String apiName = (String) request.get(REQUEST_APINAME);
        ApiMetaInfo apiInfo = apiMetaInfoComponent.getApiMetaInfoByApiName(apiName);
        //校验是否登录
        if (needLogin(apiInfo.getNeedLogin())) {
            String TJNUToken = (String) request.get(REQUEST_TOKEN);
            Boolean checkToken = getLoginToken(TJNUToken);
            if (!checkToken) {
                throw new TJNUException(TJNUConstants.USER_NOT_LOG_IN);
            }
        }

        /* 接口相关参数 */
        Class<?> className = Class.forName(apiInfo.getClassName());
        String methodName = apiInfo.getMethodName();
        String paramType = apiInfo.getParamType();
        String paramsMeta = apiInfo.getParamsMeta();

        Class[] paramTypeArr = buildParamType(paramType);
        Object[] paramValueArr = Optional.ofNullable(buildParamValue(paramTypeArr, paramsMeta, reqParams)).orElse(new Object[0]);

        Object classBean = ApplicationContextUtil.getBean(className);
        Method method = classBean.getClass().getMethod(methodName, paramTypeArr);
        Object result = method.invoke(classBean, paramValueArr);
        return result;
    }


    //根据 接口所需参数 将请求参数实例化
    public Object[] buildParamValue(Class[] paramTypeArr, String paramMetas, Map reqParams) {
        try {
            String[] paramMetaArr = JSONArray.parseArray(paramMetas).toArray(new String[0]);
            Object[] paramArr = new Object[paramMetaArr.length];
            for (int i = 0; i < paramMetaArr.length; i++) {
                if (paramTypeArr[i].getName().contains(TJNU_CLUB_OBJECT)) { // 自定义的类
                    Map<String, Object> ownReqParam = (Map<String, Object>) reqParams.get(paramMetaArr[i]);
                    Object clazz = paramTypeArr[i].newInstance();
                    BeanUtil.copyProperties(ownReqParam, clazz);
                    paramArr[i] = clazz;
                } else { // java的类
                    paramArr[i] = reqParams.get(paramMetaArr[i]);
                }
            }
            return paramArr;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    //获取参数的Class
    private Class[] buildParamType(String paramType) throws ClassNotFoundException {
        List<String> paramTypeList = JSONArray.parseArray(paramType, String.class);
        String[] paramTypeStringArr = paramTypeList.toArray(new String[0]);
        Class[] paramTypeClassArr = new Class[paramTypeStringArr.length];
        for (int i = 0; i < paramTypeStringArr.length; i++) {
            paramTypeClassArr[i] = Class.forName(paramTypeStringArr[i]);
        }
        return paramTypeClassArr;
    }


    // 验证token是否存在，TRUE-存在，并延迟2小时。
    private Boolean getLoginToken(String token) {
        String key = TJNUConstants.TOKEN_KEY_PREFIX + token + TJNUConstants.TOKEN_KEY_SUFIX;
        Boolean isExists = redisDao.exists(key);
        if (isExists) {
            redisDao.expire(key, TJNUConstants.TOKEN_EFFECTIVE_TIME);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    // 是否登录/验证token，0-不需要，1-需要
    private Boolean needLogin(Integer needLogin) {
        if (needLogin == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
