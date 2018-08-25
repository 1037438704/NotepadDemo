package pmp.tianxundai.com.notepaddemo.greendao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

public class StudentsBeanTest extends AbstractDaoTestLongPk<StudentsBeanDao, StudentsBean> {

    public StudentsBeanTest() {
        super(StudentsBeanDao.class);
    }

    @Override
    protected StudentsBean createEntity(Long key) {
        StudentsBean entity = new StudentsBean();
        entity.setId(key);
        return entity;
    }

}
