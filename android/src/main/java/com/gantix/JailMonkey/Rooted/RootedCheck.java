package com.gantix.JailMonkey.Rooted;

import android.content.Context;
import com.scottyab.rootbeer.RootBeer;
import java.util.HashMap;
import java.util.Map;

public class RootedCheck {
    private static boolean checkWithJailMonkeyMethod() {
        CheckApiVersion check;

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            check = new GreaterThan23();
        } else {
            check = new LessThan23();
        }
        return check.checkRooted();
    }

    private final boolean jailMonkeyResult;
    private final RootBeerResults rootBeerResults;

    public RootedCheck(Context context) {
        jailMonkeyResult = checkWithJailMonkeyMethod();
        rootBeerResults = new RootBeerResults(context);
    }

    public boolean isJailBroken() {
        return jailMonkeyResult || rootBeerResults.isJailBroken();
    }

    public Map<String, Object> getResultByDetectionMethod() {
        final Map<String, Object> map = new HashMap<>();

        map.put("jailMonkey", jailMonkeyResult);
        map.put("rootBeer", rootBeerResults.toNativeMap());

        return map;
    }

    private static class RootBeerResults {
        private final boolean checkRootManagementApps;
        private final boolean checkPotentiallyDangerousApps;
        private final boolean checkForSuBinary;
        private final boolean checkForDangerousProps;
        private final boolean checkForRWPaths;
        private final boolean checkTestKeys;
        private final boolean checkSuExists;
        private final boolean checkForRootNative;
        private final boolean checkForMagiskBinary;
        private final boolean checkRootCloakingApps;
        private final boolean checkForRWSystem;

        RootBeerResults(Context context) {
            final RootBeer rootBeer = new RootBeer(context);
            rootBeer.setLogging(false);

            checkRootManagementApps = rootBeer.checkRootManagementApps();
            checkPotentiallyDangerousApps = rootBeer.checkPotentiallyDangerousApps();
            checkForSuBinary = rootBeer.checkForSuBinary();
            checkForDangerousProps = rootBeer.checkForDangerousProps();
            checkForRWPaths = rootBeer.checkForRWPaths();
            checkTestKeys = rootBeer.checkTestKeys();
            checkSuExists = rootBeer.checkSuExists();
            checkForRootNative = rootBeer.checkForRootNative();
            checkForMagiskBinary = rootBeer.checkForMagiskBinary();
            checkRootCloakingApps = rootBeer.checkRootCloakingApps();
            checkForRWSystem = rootBeer.checkForRWSystem();
        }

        public boolean isJailBroken() {
            return checkRootManagementApps || checkPotentiallyDangerousApps || checkForSuBinary
                    || checkForDangerousProps || checkForRWPaths
                    || checkTestKeys || checkSuExists || checkForRootNative || checkForMagiskBinary || checkRootCloakingApps || checkForRWSystem;
        }

        public Map<String, Object> toNativeMap() {
            final Map<String, Object> map = new HashMap<>();

            map.put("checkRootManagementApps", checkRootManagementApps);
            map.put("checkPotentiallyDangerousApps", checkPotentiallyDangerousApps);
            map.put("checkForSuBinary", checkForSuBinary);
            map.put("checkForDangerousProps", checkForDangerousProps);
            map.put("checkForRWPaths", checkForRWPaths);
            map.put("checkTestKeys", checkTestKeys);
            map.put("checkSuExists", checkSuExists);
            map.put("checkForRootNative", checkForRootNative);
            map.put("checkForMagiskBinary", checkForMagiskBinary);
            map.put("checkRootCloakingApps", checkRootCloakingApps);
            map.put("checkForRWSystem", checkForRWSystem);

            return map;
        }
    }
}
