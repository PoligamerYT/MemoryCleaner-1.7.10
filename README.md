CLIENT ONLY MOD



This mod automatically calls System.gc() to free up your memory. Because explicit gc can freeze your game for a short period, the mod watches the player's movement and starts to clean up your RAM after the player stays idle for a while.

The memory cleaning process also starts when your RAM usage goes above a certain percentage (configurable) or after a long period if none of the above conditions are met (also configurable).

Also, you can use the /cleanmemory command to free up your RAM immediately.

 

More options can be seen in the in-game mod settings.

 

Note that you should NOT include -XX:+DisableExplicitGC in your JVM arguments because the mod will not function properly with it.

 

If you don't want your game to freeze when cleaning memory, you should try these JVM arguments: (-Xmx and -Xms values are omitted)

-XX:+AggressiveOpts -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSConcurrentMTEnabled -XX:ParallelGCThreads=4 -Dsun.rmi.dgc.server.gcInterval=1800000 -XX:+UnlockExperimentalVMOptions -XX:+ExplicitGCInvokesConcurrent -XX:MaxGCPauseMillis=50 -XX:+AlwaysPreTouch -XX:+UseStringDeduplication -Dfml.ignorePatchDiscrepancies=true -Dfml.ignoreInvalidMinecraftCertificates=true -XX:-OmitStackTraceInFastThrow -XX:+OptimizeStringConcat -XX:+UseAdaptiveGCBoundary -XX:NewRatio=3 -Dfml.readTimeout=90 -XX:+UseFastAccessorMethods

This enables concurrent explicit gc so your game will not freeze during memory cleanings. The JVM arguments options should be found in your Minecraft launcher.
