package com.tjnu.club.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.ApiMetaInfo;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.utils.ApplicationContextUtil;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ApiMetaInfoService apiInfoService;

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
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg());
        } catch (Throwable e) {
            String errorTip = "[ " + request.getRemoteAddr() + " ]" + " requestBody: " + JSON.toJSONString(payload) + " exception: " + e.getMessage();
            log.error(errorTip, e);
            return new ResultVO<>(TJNUConstants.BAD_GATEWAY);
        }
    }


    private Object $invoke(final Map playloadParams) throws Exception {
        Map request = (Map) playloadParams.get(REQUEST);
        Map reqParams = (Map) playloadParams.get(PARAMS);

        String apiName = (String) request.get(REQUEST_APINAME);
        ApiMetaInfo apiInfo = apiInfoService.getApiMetaInfoByApiName(apiName);
        //校验是否登录
        if (needLogin(apiInfo.getNeedLogin())) {
            String TJNUToken = (String) request.get(REQUEST_TOKEN);
            Boolean checkToken = getLoginToken(TJNUToken);
            if (!checkToken) {
                throw new TJNUException(TJNUConstants.USER_NOT_LOG_IN);
            }
        }

        /* 泛华调用相关参数 */
        Class<?> className = Class.forName(apiInfo.getClassName());
        String methodName = apiInfo.getMethodName();
        String paramType = apiInfo.getParamType();
        String paramsMeta = apiInfo.getParamsMeta();

        Class[] paramTypeArr = buildParamType(paramType);
        Object[] paramValueArr = buildParamValue(paramsMeta, reqParams);

        Object classBean = ApplicationContextUtil.getBean(className);
        Method method = classBean.getClass().getMethod(methodName,paramTypeArr);
        Object result = method.invoke(classBean,paramValueArr);
        return result;
    }

    //根据 接口所需参数 将请求参数实例化
    public Object[] buildParamValue(String paramMetas, Map reqParams) {
        String[] paramMetasArr = JSONObject.parseArray(paramMetas).toArray(new String[0]);
        Object[] paramValueArray = new Object[paramMetasArr.length];
        for (int i = 0; i < paramMetasArr.length; i++) {
            Object reqParam = reqParams.get(paramMetasArr[i]);
            paramValueArray[i] = ObjectUtils.isEmpty(reqParam) ? null : reqParam;
        }
        return paramValueArray;
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
