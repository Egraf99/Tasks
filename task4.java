import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class task4 {
    public static JSONObject fileRead(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        return (JSONObject) parser.parse(reader);
    }

    public static Map<Long, String> getValues(JSONObject obj) {
        Map<Long, String> values = new HashMap<>();
        JSONArray valuesArray = (JSONArray) obj.get("values");
        for (Object o : valuesArray) {
            JSONObject test = (JSONObject) o;
            Long id = (Long) test.get("id");
            String value = (String) test.get("value");
            values.put(id, value);
        }
        return values;
    }

    public static JSONArray putValuesInTests(JSONArray tests, Map<Long, String> values) {
        JSONArray allTest = new JSONArray();
        for (Object obj : tests) {
            JSONObject test = (JSONObject) obj;
            Long id = (Long) (test.get("id"));
            JSONArray array = (JSONArray) (test.get("values"));
            String val = (String) (test.get("value"));
            if (array != null)
                test.put("values", putValuesInTests(array, values));
            allTest.add(test);
            if (val != null)
                test.put("value", values.get(id));
        }
        return allTest;
    }

    public static void fileWrite(JSONObject json) {
        try (FileWriter file = new FileWriter("report.json")) {
            file.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        String testsPath = args[0], valuesPath = args[1];
        JSONObject testsJSON = fileRead(testsPath), valuesJSON = fileRead(valuesPath);
        Map<Long, String> valuesMap = getValues(valuesJSON);
        JSONArray allTests = (JSONArray) testsJSON.get("tests");
        JSONArray allArrays = putValuesInTests(allTests, valuesMap);
        JSONObject answer = new JSONObject();
        answer.put("tests", allArrays);
        fileWrite(answer);
    }
}
