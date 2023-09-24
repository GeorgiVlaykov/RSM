package org.rsm.threads.conf;

import java.util.Set;

public class Conf {
    public static final Set<String> fileExtensionsFilter = Set.of("cmd", "com", "dll", "dmg", "exe", "iso", "jar", "js");
    public static final int MB = 1000000;
    public static final int maxCapacity = 100 * MB;
    public static final int threadPoolSize = 8;
}
