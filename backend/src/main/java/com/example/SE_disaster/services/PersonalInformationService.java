package com.example.SE_disaster.services;

import com.example.SE_disaster.mappers.PersonalInformationMapper;
import com.example.SE_disaster.mappers.UserMapper;
import com.example.SE_disaster.models.PersonalInformation;
import com.example.SE_disaster.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInformationService
{
    @Autowired
    PersonalInformationMapper personalInformationMapper;
    @Autowired
    UserMapper userMapper;
    
    public boolean insertPersonalInformation(Long uid,String phone_number,String email,String nick_name)
    {
        if(personalInformationMapper.selectById(uid) != null)
        {
            return false;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.uid = uid;
        personalInformation.phone_number = phone_number;
        personalInformation.email = email;
        personalInformation.nick_name = nick_name;
        personalInformationMapper.insert(personalInformation);
        return true;
    }
    
    public boolean updatePersonalInformation(Long uid,String phone_number,String email,String nick_name)
    {
        if(personalInformationMapper.selectById(uid) == null)
        {
            return false;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.uid = uid;
        personalInformation.phone_number = phone_number;
        personalInformation.email = email;
        personalInformation.nick_name = nick_name;
        personalInformationMapper.updateById(personalInformation);
        return true;
    }
    
    public PersonalInformation getPersonalInformation(Long uid)
    {
        return personalInformationMapper.selectById(uid);
    }
    
    public boolean changePassword(Long uid,String password)
    {
        User user = userMapper.selectById(uid);
        if(user == null)
        {
            return false;
        }
        user.setPassword(password);
        userMapper.updateById(user);
        return true;
    }
}
