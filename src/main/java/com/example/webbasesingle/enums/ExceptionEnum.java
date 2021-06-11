package com.example.webbasesingle.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通信返回码类型
 */
public enum ExceptionEnum {
	SUCCESS("请求成功", 1008),
	FAILURE("请求失败", 1009),
	INVALID("token失效",1010),
	PARAM_ERROR("参数{0}错误",1011),
	PARAMS_ERROR("参数错误",1011),


	EXCEL_FAILURE("解析失败",1012),
    PRODUCT_IS_NULL("产品{0}不存在",1013),
	CUSTOMER_IS_NULL("客户{0}不存在",1015),

	QUALIFIED_INVESTOR_CERTIFICATION("请完成合格投资者认证",1016),

	RISK_LEVEL_MISMATCH("风险等级不匹配",1017),

	RISK_ASSESSMENT("请完成风险测评",1018),

	REAL_NAME_LEGALIZE("请完成实名认证",1019),

	NON_INVESTOR("非该产品的投资者人",1020),

	REGISTER_PHONE("该手机号已被注册过：",1021),

	AUTHORIZATION_FAILED("微信授权失败请重新授权!",1022),

	WEI_XIN_USER_INFO("获取微信用户信息失败!",1023),

	/**
	 * fof准入流程 start
	 */
	ENTER_FLOW_EXIST("该准入流程已存在", 1500),

	TOKEN("token已失效，请重新登陆",1014),

	ACCOUNT_FREEZE("账号已被冻结",1015),

	ONE_LOGIN("初次登录",1026),
	RESET_PASSWORD_NOT_MATCH("两次输入的密码不正确",1027),
	REPEATED_EMERGENCE("该手机号已被注册",1028),
	REPEATED_EMERGENCE_CARDNUMBER("证件号码已经绑定",1029),
	THE_ACCOUNT_HAS_BEEN_DISABLED("此账号已禁用,请联系管理人!",1030),
	/**
	 * fof准入流程 end
	 */

	/**
	 * 对接老平台
	 */
	NO_TEMPLATES_WERE_CREATED("无创建任何模板!",1031),
	TEMPLATES_ARE_BEING_CREATED("模板均在创建中!",1032),
	NO_NEW("暂无新增!",1033),
	ALL_SYNCHRONIZATION("全部同步!",1034),
	PART_OF_THE_SYNCHRONIZATION("部分同步!",1035),
	SENSITIVE_WORD_ERROR("短信出现敏感词，请重新输入",1036),
	;

	/**
	 * 描述
	 */
	private String message;
	/**
	 * 返回码
	 */
	private Integer code;
	  private static Map<Integer, ExceptionEnum> enumMap = new HashMap();
	    public static ExceptionEnum getEnum(int code){
	        return enumMap.get(code);
	    }

	    public static String getMessage(int code){
	        ExceptionEnum en = getEnum(code);
	        if(en == null){
	            return getMessage(88888);
	        }
	        return en.getMessage();
	    }
	private ExceptionEnum(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	 static{
	        ExceptionEnum[] enums = values();
	        ExceptionEnum[] newEnums = enums;

	        for (int i = 0; i < newEnums.length; i++) {
	            ExceptionEnum en = newEnums[i];
	            enumMap.put(en.getCode(), en);
	        }
	    }
}
