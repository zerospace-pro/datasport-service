package com.zerospace.datasport.config.mybatis;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @Author scott
 * @Date  2019-01-19
 *
 */
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		log.debug("------sqlId------" + sqlId);
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		log.debug("------sqlCommandType------" + sqlCommandType);

		if (parameter == null) {
			return invocation.proceed();
		}
		if (SqlCommandType.INSERT == sqlCommandType) {
			Field[] fields = oConvertUtils.getAllFields(parameter);
			for (Field field : fields) {
				log.debug("------field.name------" + field.getName());
				try {
					// 获取登录用户信息
					LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
					if ("createBy".equals(field.getName())) {
						field.setAccessible(true);
						Object local_createBy = field.get(parameter);
						field.setAccessible(false);
						if (local_createBy == null || local_createBy.equals("")) {
							String createBy = "admin";
							if (sysUser != null) {
								// 登录账号
								createBy = sysUser.getUsername();
							}
							if (oConvertUtils.isNotEmpty(createBy)) {
								field.setAccessible(true);
								field.set(parameter, createBy);
								field.setAccessible(false);
							}
						}
					}
					//注入删除状态
					if ("delFlag".equals(field.getName())) {
						field.setAccessible(true);
						Object local_delFlag = field.get(parameter);
						field.setAccessible(false);
						if (local_delFlag == null || local_delFlag.equals("")) {
							String delFlag = "0";
							field.setAccessible(true);
							field.set(parameter, delFlag);
							field.setAccessible(false);
						}
					}
					// 注入创建时间
					if ("createTime".equals(field.getName())) {
						injectionTime(field, parameter);
					}
					//注入部门编码
					if ("sysOrgCode".equals(field.getName())) {
						field.setAccessible(true);
						Object local_sysOrgCode = field.get(parameter);
						field.setAccessible(false);
						if (local_sysOrgCode == null || local_sysOrgCode.equals("")) {
							String sysOrgCode = "";
							// 获取登录用户信息
							if (sysUser != null) {
								// 登录账号
								sysOrgCode = sysUser.getOrgCode();
							}
							if (oConvertUtils.isNotEmpty(sysOrgCode)) {
								field.setAccessible(true);
								field.set(parameter, sysOrgCode);
								field.setAccessible(false);
							}
						}
					}
				} catch (Exception ignored) {
					log.error("Mybatis拦截注入数据异常",ignored);
				}
			}
		}
		if (SqlCommandType.UPDATE == sqlCommandType) {
			Field[] fields = null;
			if (parameter instanceof ParamMap) {
				ParamMap<?> p = (ParamMap<?>) parameter;
				parameter = p.get("param1");
				fields = oConvertUtils.getAllFields(parameter);
			} else {
				fields = oConvertUtils.getAllFields(parameter);
			}

			for (Field field : fields) {
				log.debug("------field.name------" + field.getName());
				try {
					if ("updateBy".equals(field.getName())) {
						field.setAccessible(true);
						Object local_updateBy = field.get(parameter);
						field.setAccessible(false);
						if (local_updateBy == null || local_updateBy.equals("")) {
							String updateBy = "admin";
							// 获取登录用户信息
							LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
							if (sysUser != null) {
								// 登录账号
								updateBy = sysUser.getUsername();
							}
							if (oConvertUtils.isNotEmpty(updateBy)) {
								field.setAccessible(true);
								field.set(parameter, updateBy);
								field.setAccessible(false);
							}
						}
					}
					if ("updateTime".equals(field.getName())) {
						injectionTime(field, parameter);
					}
				} catch (Exception ignored) {
					log.error("Mybatis拦截注入数据异常",ignored);
				}
			}
		}
		return invocation.proceed();
	}

	private void injectionTime(Field field, Object parameter) throws IllegalAccessException {
		field.setAccessible(true);
		Object local_updateDate = field.get(parameter);
		field.setAccessible(false);
		if (local_updateDate == null || local_updateDate.equals("")) {
			field.setAccessible(true);
			field.set(parameter, new Date());
			field.setAccessible(false);
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
