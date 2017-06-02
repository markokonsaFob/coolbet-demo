package impl

import groovy.json.internal.LazyMap

class TestDataManager {

    private static ThreadLocal<LazyMap> testData = new ThreadLocal<>([:])

    /**
     * Saves a key-value pair to map for given device
     * @param key Key for the value
     * @param value value to be saved
     */
    static void setTestData(String key, String value) {
        getTestData().put(key, value)
    }
    /**
     * Returns value from device specific map
     * @param key Key for the value
     * @return String value from map
     */
    static String getValue(String key) {
        getTestData().get(key)
    }

    private static LazyMap getTestData() {
        if (testData.get() == null) {
            testData.set([:] as LazyMap)
        }
        testData.get() as LazyMap
    }
}