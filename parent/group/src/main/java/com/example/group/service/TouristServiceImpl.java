package com.example.group.service;

import com.example.group.dao.TouristDao;
import com.example.group.pojo.GroupActivity;
import com.example.group.pojo.Tourist;
import com.example.group.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TouristServiceImpl implements TouristService {
	//注解注入CustomerDao
	@Autowired
	private TouristDao touristDao;
	//查询客户
	@Override
	public GroupActivity findActivityWithTourists(Integer id){
		// TODO Auto-generated method stub
		return touristDao.findActivityWithTourists(id);
	}
	@Override
	public List<GroupActivity> findAllActivityWithTourists() {
		// TODO Auto-generated method stub
		return touristDao.findAllActivityWithTourists();
	}
	@Override
	public void insertActivity(GroupActivity activity) {
		// TODO Auto-generated method stub
		touristDao.insertActivity(activity);
	}
	@Override
	public void insertTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.insertTourist(tourist);
	}
	@Override
	public void updateTouristPay(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.updateTouristPay(tourist);
	}
	@Override
	public List<GroupActivity> findAllActivity() {
		// TODO Auto-generated method stub
		return touristDao.findAllActivity();
	}
	@Override
	public void AddAAPay(int activity_id,double pay) {
		// TODO Auto-generated method stub
		touristDao.AddAAPay(activity_id,pay);
	}
	@Override
	public List<Tourist> findAllTourist() {
		// TODO Auto-generated method stub
		return touristDao.findAllTourist();
	}
	@Override
	public Double findActivityBasicPay(Integer activity_id) {
		// TODO Auto-generated method stub
		return touristDao.findActivityBasicPay(activity_id);
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		touristDao.addUser(user);
	}
	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		return touristDao.findUser(name);
	}
	@Override
	public void updateActivityNumber(Integer id) {
		// TODO Auto-generated method stub
		touristDao.updateActivityNumber(id);
	}
	@Override
	public List<GroupActivity> findActivityByLeader(String leader) {
		// TODO Auto-generated method stub
		return touristDao.findActivityByLeader(leader);
	}
	@Override
	public void AddAAPay1(int activity_id,double pay) {
		// TODO Auto-generated method stub
		touristDao.AddAAPay1(activity_id,pay);
	}
	@Override
	public Tourist findTourist(int id,int activity_id) {
		// TODO Auto-generated method stub
		return touristDao.findTourist(id,activity_id);
	}
	@Override
	public List<GroupActivity> findActivityByName(String name) {
		// TODO Auto-generated method stub
		return touristDao.findActivityByName(name);
	}
	@Override
	public GroupActivity findActivityByName1(String name) {
		// TODO Auto-generated method stub
		return touristDao.findActivityByName1(name);
	}
	@Override
	public List<Tourist> findTouristByName(String name) {
		// TODO Auto-generated method stub
		return touristDao.findTouristByName(name);
	}
	@Override
	public GroupActivity findActivityById(Integer activity_id) {
		// TODO Auto-generated method stub
		return touristDao.findActivityById(activity_id);
	}
}
