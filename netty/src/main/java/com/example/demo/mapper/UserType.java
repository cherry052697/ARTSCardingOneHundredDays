package com.example.demo.mapper;


public enum UserType {
   
    /**
     * 医生
     */
    DOCTOR,
    /**
     * 患者
     */
    PATIENT,
    /**
     * 地推
     */
    INTRODUCER,
    
    /**
     * 39地推
     */
    INTRODUCER_39,
    
    /**
     * 超级地推，这种用户推荐的用户只能是地推
     */
    SUPPER_INTRODUCER,
  
    /**
     * 39超级地推，这种用户推荐的用户只能是39地推
     */
    SUPPER_INTRODUCER_39,
	
    /**
     * assistant，大夫助理
     */
    ASSISTANT,
    
    /**
     * 徒弟
     */
    APPRENTICE,
    
    /**
     * 小诊所老板
     */
    CLINICBOSS,

    /**
     * 小诊所医生
     */
    CLINICDOCTOR,

    /**
     * 小诊所患者
     */
    CLINICPATIENT,
    
    /**
     * 小诊所总部管理员
     */
    CLINICMANAGER,
    
    /**
     * 小诊所审核员
     */
    CLINICASSESSOR,
    
    /**
     * 小诊所财务
     */
    CLINICACCOUNTANT
}
