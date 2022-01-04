package data;

import java.util.HashMap;
import java.util.Map;

public class TypicodeData {

	public static Map<String, Object> expectedDataSetUp(){
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("userId", 777);
		expectedData.put("title", "Tidy your room 777");
		expectedData.put("completed", false);
		return expectedData;
	}

	public static Map<String, Object> expectedDataSetUp(int userId, String title, boolean isCompleted){
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("userId", userId);
		expectedData.put("title", title);
		expectedData.put("completed", isCompleted);
		return expectedData;
	}

}
