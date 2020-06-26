package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.ShareMapper;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.ShareService;

@Service
@Transactional
public class ShareServiceImpl implements ShareService{
 
	@Autowired
	private ShareMapper shareMapper;

	@Override
	public int insShare(Share share) {
		// TODO Auto-generated method stub
		return shareMapper.insShare(share);
	}

	@Override
	public int insStatus(Orders orders) {
		// TODO Auto-generated method stub
		return shareMapper.insStatus(orders);
	}

	@Override
	public List<Share> showSharesList() {
		// TODO Auto-generated method stub
		return shareMapper.showSharesList();
	}

	@Override
	public Share showShare(Share share) {
		// TODO Auto-generated method stub
		return shareMapper.showShare(share);
	}

	@Override
	public Users selNickNameAva(String uid) {
		// TODO Auto-generated method stub
		return shareMapper.selNickNameAva(uid);
	}

	@Override
	public Orders selStartTimeStatus(Share share) {
		// TODO Auto-generated method stub
		return shareMapper.selStartTimeStatus(share);
	}
	
	
}
