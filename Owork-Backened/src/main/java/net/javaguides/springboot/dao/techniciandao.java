package net.javaguides.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.technician;


@Repository
public class techniciandao {
    
    @Autowired
    private JdbcTemplate jdbctemplate;
    
    public void techniciandao()
    {
      
       
    }
    public int createtabletechnician()
    {
       String query="CREATE TABLE  IF NOT EXISTS technician\n"
               + "    (name VARCHAR(255) ,\n"
               + "    dob Date NOT NULL,\n"
               + "    phone Varchar(15) ,\n"
               +"     rating int , \n"
               + "    address VARCHAR(255) ,\n"
               + "    email VARCHAR(255) NOT NULL,\n"
               + "    city VARCHAR(255) NOT NULL,\n"
               + "    state VARCHAR(255) NOT NULL,\n"
               + "    zip VARCHAR(255) NOT NULL,\n"
               + "    areaofexpertise VARCHAR(255) , \n"
               + "    password VARCHAR(255) , \n"
               + "    PRIMARY KEY(email)  \n"
               + ");";
       int update =  this.jdbctemplate.update(query);
       return update;
        
    }
    public void inserttechnician(technician tch)
    {
        String query="insert into technician(name,dob,phone,rating,address,email,city,state,zip,areaofexpertise,password) values(?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbctemplate.update(query,tch.getName(),tch.getDob(),tch.getPhone(),0,tch.getAddress(),tch.getEmail(),tch.getCity(),tch.getState(),tch.getZip(),tch.getAreaofexpertise(),tch.getPassword());
        
        
    }
    
    public technician findbyemail(String email)
    {
        String query = "select * from technician where email = ? ";
        return this.jdbctemplate.queryForObject(query, new BeanPropertyRowMapper<technician>(technician.class),email);
        
    }
    
    public int countbyemail(String email)
    {
        String query="select count(*) from technician where email = ? ";
        return this.jdbctemplate.queryForObject(query,Integer.class,email);
    }
    public List<technician> getall()
    {
        String sql="select * from technician";
        return this.jdbctemplate.query(sql, new BeanPropertyRowMapper<technician>(technician.class));
    }
    
    public List<technician> getbyworkdomain(String work)
    {
        String sql="select * from technician where areaofexpertise= ? ";
        return this.jdbctemplate.query(sql, new BeanPropertyRowMapper<technician>(technician.class),work);
    }
    
   
    public int counttotalTech()
    {
        return this.jdbctemplate.queryForObject("select count(*) from technician",Integer.class);
    }
    public int matchthepassword(String email, String password) {
       
        String sql="select count(*) from technician where email = ? && password = ? ";
        return this.jdbctemplate.queryForObject(sql, Integer.class,email,password);
        
    }
    
    
}
