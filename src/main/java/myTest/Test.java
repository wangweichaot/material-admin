package myTest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.sjlexpress.wl.dao.SystemUserMapper;

public class Test {
	
	@Autowired
	SystemUserMapper systemUserMapper;
	
	public static void main(String[] args) {
		
//		List<Persion> list = Lists.newArrayList();
//		list.add(new Persion("张三",25));
//		list.add(new Persion("李四",21));
//		list.add(new Persion("王五",24));
//		
//		List<Persion> list2 = list.stream().filter(e -> e.getAge() == 21).collect(Collectors.toList());
//		
//		list2.forEach(e -> System.out.println(e));
		
		
		
	}
	
}




