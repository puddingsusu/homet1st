import java.util.*;

public class ValidateNestedJSON {

    /**
     * Validasi apakah sebuah Object merupakan struktur JSON yang valid.
     * Struktur yang valid hanya boleh berisi:
     * - Map dengan key String dan value yang valid (nested object)
     * - List atau Array (nested array)
     * - Tipe data primitif JSON (string, number, boolean, null)
     *
     * Time Complexity: O(n) dimana n adalah total jumlah elemen dalam struktur
     * Space Complexity: O(d) dimana d adalah kedalaman maksimum rekursi
     *
     * @param input Object yang akan divalidasi
     * @return true jika input merupakan struktur JSON valid, false otherwise
     */
    public static boolean isValidJson(Object input) {
        // Null adalah valid JSON value
        if (input == null) {
            return true;
        }

        // Cek tipe data primitif JSON
        if (isPrimitiveJsonType(input)) {
            return true;
        }

        // Cek jika input adalah Map (JSON object)
        if (input instanceof Map) {
            return isValidJsonObject((Map<?, ?>) input);
        }

        // Cek jika input adalah List atau Array (JSON array)
        if (input instanceof List || input.getClass().isArray()) {
            return isValidJsonArray(input);
        }

        // Tipe data lain tidak valid untuk JSON
        return false;
    }

    /**
     * Validasi Map sebagai JSON object
     * Key harus String dan value harus valid JSON value
     */
    private static boolean isValidJsonObject(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            // Key harus String
            if (!(key instanceof String)) {
                return false;
            }

            // Value harus valid JSON value (rekursif)
            if (!isValidJson(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validasi List atau Array sebagai JSON array
     * Setiap elemen harus valid JSON value
     */
    private static boolean isValidJsonArray(Object array) {
        if (array instanceof List) {
            List<?> list = (List<?>) array;
            for (Object element : list) {
                if (!isValidJson(element)) {
                    return false;
                }
            }
        } else if (array.getClass().isArray()) {
            // Handle array
            int length = java.lang.reflect.Array.getLength(array);
            for (int i = 0; i < length; i++) {
                Object element = java.lang.reflect.Array.get(array, i);
                if (!isValidJson(element)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cek apakah object merupakan tipe data primitif JSON
     * JSON primitive types: String, Number, Boolean, null
     */
    private static boolean isPrimitiveJsonType(Object obj) {
        return obj instanceof String ||
               obj instanceof Number ||
               obj instanceof Boolean ||
               obj == null;
    }

    // Method untuk testing
    public static void main(String[] args) {
        System.out.println("=== Test Cases for JSON Validation ===\n");

        // Test Case 1: Valid JSON object sederhana
        Map<String, Object> validObject1 = new HashMap<>();
        validObject1.put("name", "Bejo");
        validObject1.put("age", 30);
        validObject1.put("active", true);
        validObject1.put("balance", null);

        System.out.println("Test 1 - Valid simple object: " + isValidJson(validObject1));

        // Test Case 2: Valid nested JSON object
        Map<String, Object> validObject2 = new HashMap<>();
        Map<String, Object> address = new HashMap<>();
        address.put("street", "bojong gede");
        address.put("city", "bogot");
        address.put("zipcode", 10001);

        validObject2.put("person", address);
        validObject2.put("tags", Arrays.asList("developer", "java", "json"));
        validObject2.put("scores", new int[]{90, 85, 95});

        System.out.println("Test 2 - Valid nested object: " + isValidJson(validObject2));

        // Test Case 3: Valid JSON array
        List<Object> validArray = Arrays.asList(
            "string",
            42,
            true,
            null,
            Map.of("nested", "value"),
            Arrays.asList(1, 2, 3)
        );

        System.out.println("Test 3 - Valid JSON array: " + isValidJson(validArray));

        // Test Case 4: Valid complex structure
        Map<String, Object> complexValid = new HashMap<>();
        List<Object> nestedArray = new ArrayList<>();
        Map<String, Object> nestedObject = new HashMap<>();
        nestedObject.put("deep", "value");
        nestedArray.add(nestedObject);
        nestedArray.add("string");
        complexValid.put("array", nestedArray);
        complexValid.put("primitive", 123);

        System.out.println("Test 4 - Valid complex structure: " + isValidJson(complexValid));

        // Test Case 5: Invalid - Map dengan non-String key
        Map<Object, Object> invalidMap1 = new HashMap<>();
        invalidMap1.put(123, "number as key");  // Key bukan String
        System.out.println("Test 5 - Invalid non-String key: " + isValidJson(invalidMap1));

        // Test Case 6: Invalid - Map dengan invalid value
        Map<String, Object> invalidMap2 = new HashMap<>();
        invalidMap2.put("validKey", new Object());  // Object bukan JSON type
        System.out.println("Test 6 - Invalid value type: " + isValidJson(invalidMap2));

        // Test Case 7: Invalid - Array dengan invalid element
        List<Object> invalidArray = new ArrayList<>();
        invalidArray.add("valid");
        invalidArray.add(new HashMap<>());  // Valid Map
        invalidArray.add(new Thread());     // Invalid Object
        System.out.println("Test 7 - Invalid array element: " + isValidJson(invalidArray));

        // Test Case 8: Valid primitive types
        System.out.println("Test 8a - String primitive: " + isValidJson("hello"));
        System.out.println("Test 8b - Number primitive: " + isValidJson(42));
        System.out.println("Test 8c - Boolean primitive: " + isValidJson(true));
        System.out.println("Test 8d - Null primitive: " + isValidJson(null));

        // Test Case 9: Invalid primitive types
        System.out.println("Test 9a - Invalid Thread object: " + isValidJson(new Thread()));
        System.out.println("Test 9b - Invalid Class object: " + isValidJson(ValidateNestedJSON.class));

        // Test Case 10: Array of primitives
        System.out.println("Test 10 - String array: " + isValidJson(new String[]{"a", "b", "c"}));
        System.out.println("Test 11 - int array: " + isValidJson(new int[]{1, 2, 3}));
        System.out.println("Test 12 - Object array with valid elements: " +
            isValidJson(new Object[]{"string", 42, true}));
        System.out.println("Test 13 - Object array with invalid elements: " +
            isValidJson(new Object[]{"string", new Thread()}));
    }
}