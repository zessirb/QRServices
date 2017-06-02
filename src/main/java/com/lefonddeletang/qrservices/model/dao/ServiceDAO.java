package com.lefonddeletang.qrservices.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;

public class ServiceDAO {
	
	static public Optional<List<ServiceBean>> getServices() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ServiceBean> serviceList = (List<ServiceBean>) session.createCriteria(ServiceBean.class).list();
		return Optional.ofNullable(serviceList);
	}
}
