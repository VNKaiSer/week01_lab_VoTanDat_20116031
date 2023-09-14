package vn.edu.iuh.fit.lab_week01.constant;

import java.util.HashMap;
import java.util.Map;

public interface Constant {
    Map<String, Integer> mapStatus = new HashMap<>(
            Map.of(
                    "ACTIVE", 1,
                    "DEACTIVE", 0,
                    "DELETED", -1
            )
    );

    Map<String, Integer> isgrant = new HashMap<>(
            Map.of(
                    "ENABLED", 1,
                    "DISABLED", 0
            )
    );
}
