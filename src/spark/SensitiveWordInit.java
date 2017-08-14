package spark;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 初始化白名单库，将白名单加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author :
 * @Date ： 2014年4月20日 下午2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit(){
		super();
	}

	/**
	 * @author
	 * @date 2014年4月20日 下午2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(){
		try {

			//读取白名单库
            Set<String> keyWordSet = Sets.newHashSet();
            keyWordSet.add("xxxxxbbs.com");
            keyWordSet.add("15feng.cn");
			//将白名单库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
			//spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取白名单库，将白名单放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @author
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet  白名单库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化白名单容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取

				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}

	/**
	 * 读取白名单库中的内容，将内容添加到set集合中
	 * @author
	 * @date 2014年4月20日 下午2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
//	@SuppressWarnings("resource")
//	private Set<String> readSensitiveWordFile() throws Exception{
//		Set<String> set = null;
//
//		File file = new File("D:\\SensitiveWord.txt");    //读取文件
//		InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
//		try {
//			if(file.isFile() && file.exists()){      //文件流是否存在
//				set = new HashSet<String>();
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String txt = null;
//				while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
//					set.add(txt);
//				}
//			}
//			else{         //不存在抛出异常信息
//				throw new Exception("白名单库文件不存在");
//			}
//		} catch (Exception e) {
//			throw e;
//		}finally{
//			read.close();     //关闭文件流
//		}
//		return set;
//	}
}
