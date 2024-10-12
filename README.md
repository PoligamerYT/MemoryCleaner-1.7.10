
# MemoryCleaner (Backport for Minecraft 1.7.10)

**IMPORTANT: THIS MOD IS NOT FOR EVERYONE!**  
Only use this if you are experiencing lag caused by high RAM usage (common with very large modpacks) or crashes caused by `OutOfMemoryError`. Otherwise, this mod will negatively impact your game performance, as explicit garbage collection (GC) can cause temporary freezes without providing any benefit if you have ample RAM.

For the best experience, it's recommended to use the suggested JVM arguments listed below.

## About
MemoryCleaner is a client-only mod that automatically calls `System.gc()` to free up memory. 

The cleanup process also starts when RAM usage exceeds a specified percentage (configurable) or after an extended period if the above conditions are not met (also configurable).  

You can also use the `/cleanmemory` command to manually initiate memory cleanup at any time.

Additional configuration options are available in the in-game mod settings.

**Note:** Do **not** include `-XX:+DisableExplicitGC` in your JVM arguments, as it will prevent the mod from functioning.

## Recommended JVM Arguments
If you want to avoid game freezes during memory cleaning, consider using the following JVM arguments in your Minecraft launcher. (Adjust `-Xmx` and `-Xms` values as needed):

```
-XX:+AggressiveOpts
-XX:+UseConcMarkSweepGC
-XX:+UseParNewGC
-XX:+CMSConcurrentMTEnabled
-XX:ParallelGCThreads=4
-Dsun.rmi.dgc.server.gcInterval=1800000
-XX:+UnlockExperimentalVMOptions
-XX:+ExplicitGCInvokesConcurrent
-XX:MaxGCPauseMillis=50
-XX:+AlwaysPreTouch
-XX:+UseStringDeduplication
-Dfml.ignorePatchDiscrepancies=true
-Dfml.ignoreInvalidMinecraftCertificates=true
-XX:-OmitStackTraceInFastThrow
-XX:+OptimizeStringConcat
-XX:+UseAdaptiveGCBoundary
-XX:NewRatio=3
-Dfml.readTimeout=90
-XX:+UseFastAccessorMethods
```

These options enable concurrent explicit GC to reduce potential game freezes during memory cleanup.

## Credits
Original mod by **TCreopargh**.  
Backported for Minecraft 1.7.10.
