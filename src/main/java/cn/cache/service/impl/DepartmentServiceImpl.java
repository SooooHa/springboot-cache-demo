package cn.cache.service.impl;

import cn.cache.entity.Department;
import cn.cache.dao.DepartmentDao;
import cn.cache.service.DepartmentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Department)表服务实现类
 *
 * @author makejava
 * @since 2020-02-12 21:10:56
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;


    /**
     * 缓存
     * 将方法的结果进行缓存，以后要相同的数据，直接从缓存中取出不用调用方法
     *
     * CacheManager管理多个Cache组件，对缓存对真正CRUD操作在Cache组件中，每一个缓存组件都有自己唯一的名字
     * 属性
     *  cacheNames/value：指定缓存组件的名字,将方法的返回结果放在哪个缓存中,数组的方式,可以指定多个缓存
     *  key： 缓存数据使用的key；默认使用方法参数的值
     *         SpEL：#id 参数id的值
     *  keyGenerator: key生成器;可以使用自己制定key的生成器的组件id
     *          key和keyGenerator二选一
     *  cacheManager: 指定缓存管理器 或者 cacheResolver指定获取解析器
     *  condition: 指定符合条件的情况下才缓存  condition="#a0>1" 第一个参数的值大于1
     *  unless: 否定缓存;当unless指定的条件为true,方法的返回值就不会被缓存 unless="#result==null"  unless = "#a0==2" 第一个参数等于2的时候不缓存
     *  sync： 是否使用异步  异步的时候不支持unless
     *
     *  3. 默认配置生效：SimpleCacheConfiguration：
     *  4. 容器中注册一个CacheManager：ConcurrentMapCacheManager
     *  5. 可以获取和创建ConcurrentMapCache类型的缓存组件,作用是将数据保存在ConcurrentMap中
     *
     *  运行流程@Cacheable
     *  1. 方法运行之前,先去查询Cache缓存组件,按照cacheName指定名字获取(CacheManager先获取相应的缓存)第一次获取缓存如果没有Cache组件会自动创建
     *  2. 去Cache查找缓存的内容,使用一个key,默认就是方法的参数
     *      key是按照某种策略生成的,默认使用keyGenerator生成,默认使用SimpleKeyGenerator生成key
     *      SimpleKeyGenerator生成key的默认策略
     *      如果没有参数: key = new SimpleKey();
     *      如果有一个参数: key = 参数的值
     *      如果有多个参数: key = new SimpleKey(params)
     *  3. 没有查到缓存就调用目标方法
     *  4. 将目标方法返回的结果,放进缓存中
     * @Cacheable 标注的方法执行之前先来检查缓存中有没有这个数据,默认按照参数的值作为key查询缓存
     * 如若没有就运行方法并将结果放入缓存,以后再来调用就可以直接使用缓存中的数据
     *
     * 核心:
     *  1. 使用CacheManager[ConcurrentMapCacheManager] 按照名字得到cache[ConcurrentMapCache]组件
     *  2. key使用keyGenerator生成,默认是SimpleKeyGenerator生成
     */




    /**
     * 通过ID查询单条数据,只缓存id>2的数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Cacheable(cacheNames = {"dept"},keyGenerator = "myKeyGenerator",condition = "#id>1",unless = "#a0==2")
    @Override
    public Department queryById(Integer id) {
        return this.departmentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Department> queryAllByLimit(int offset, int limit) {
        return this.departmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department insert(Department department) {
        this.departmentDao.insert(department);
        return department;
    }

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department update(Department department) {
        this.departmentDao.update(department);
        return this.queryById(department.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.departmentDao.deleteById(id) > 0;
    }
}