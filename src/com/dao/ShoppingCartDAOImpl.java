package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.OurUser;


public class ShoppingCartDAOImpl extends HibernateDaoSupport implements  ShoppingCartDAO{
	
	@Autowired
	private com.util.encryption.intrface.EncryptionVisitor encrypt;
	
	@Autowired
	private com.util.encryption.intrface.DecryptionVisitor decrypt;

	// load user
	public com.model.OurUser loadUser(String userid, int storeid, boolean shouldDecrypted){		
		Criteria crit = getSession().createCriteria(OurUser.class);
		crit.add( Restrictions.eq("userid", userid));
		com.model.OurUser retUser = (com.model.OurUser)crit.uniqueResult();
		retUser.setEncrypted(true);
		if(true == shouldDecrypted){
			retUser = decrypt.visit(retUser);
		}
		return retUser;
	}
	
	// load user
	public com.model.OurUser loadUser(String userid, int storeid){		
		return loadUser(userid,storeid,true);
	}	
	
	// Save user
	public void saveUser(com.model.OurUser DBUser, int storeid) throws DataAccessException {
			OurUser user = loadUser(DBUser.getUserid(),storeid);
			DBUser.setId(user.getId());
			DBUser.setSalt(user.getSalt());
			DBUser.setRoles(user.getRoles());
			DBUser = encrypt.visit(DBUser);
			getHibernateTemplate().update(DBUser);
	}
	
	// Save user
	public void addUser(com.model.OurUser DBUser) throws DataAccessException {
			DBUser = encrypt.visit(DBUser);
			try{
				getHibernateTemplate().saveOrUpdate(DBUser);
			}catch(DataAccessException e){
				e.printStackTrace();
				throw e;
			}
	}
	
	// Save user
	public void saveWOutEncryption(com.model.OurUser DBUser) throws DataAccessException {
		getHibernateTemplate().update(DBUser);
	}	
	
	public List<String> getAllUserIdList() throws Exception {
		
		List<String> retList = null;
		
		SQLQuery query = getSession().createSQLQuery("select userid from user where roles = 1");
		retList = (List<String>)query.list();
		return retList;
	}
	
	public List<String> getAdminList() throws DataAccessException {
		
		List<String> retList = null;
		SQLQuery query = getSession().createSQLQuery("select userid from user where roles = 2");
		retList = (List<String>)query.list();
		return retList;
	}	
	
	// Get Super user list
	public List<String> getSuperUserList() throws DataAccessException{
		List<String> retList = null;
		SQLQuery query = getSession().createSQLQuery("select userid from user where roles = 3");
		retList = (List<String>)query.list();
		return retList;
	}
}