package com.shinkson47.datacwk.main;

import com.shinkson47.datacwk.lib.collection.pool.StudentPool;

/**
 * <h1>Default test data for demonstration. Taken directly from the specification.</h1>
 * <br>
 * <p>
 *
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 21/11/2020</a>
 * @version 1
 * @since v1
 */
public class GlobalData {

    /**
     * All students from the specification example.
     */
    public static final String[] SPEC_TABLE_ONE = new String[]{
            // Level 6
            "P2531111",
            "P2530001",

            // Level 5
            "P2531120",
            "P2532211",
            "P2534141",

            "P2530230",

            // Level 4
            "P2530201",
            "P2530150",
            "P2530190",
            "P2530210",
            "P2530229",
            "P2530250"
    };

    /**
     * <h2>Configures the relationships as found in the specification at Table 1</h2>
     */
    public static void CreateDefaultRelationships() {

        // Level 6
        // P2531111, Two mentees.
        StudentPool.Global.setRelationship("P2531111", "P2531120");
        StudentPool.Global.setRelationship("P2531111", "P2532211");

        // P2530001, Two mentees
        StudentPool.Global.setRelationship("P2530001", "P2534141");
        StudentPool.Global.setRelationship("P2530001", "P2530230");

        // Level 5
        // P2531120, One mentee
        StudentPool.Global.setRelationship("P2531120", "P2530201");

        // P2530150, Three mentees
        StudentPool.Global.setRelationship("P2532211", "P2530150");
        StudentPool.Global.setRelationship("P2532211", "P2530190");
        StudentPool.Global.setRelationship("P2532211", "P2530210");

        // P2534141, Two mentees.
        StudentPool.Global.setRelationship("P2534141", "P2530229");
        StudentPool.Global.setRelationship("P2534141", "P2530250");
    }

    // Common color value used in the JFX UI.
    public static final float col = 42 / 255.0f;
}
