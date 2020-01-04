package de.christianbernstein.universe.provider;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public final class ServiceProvider {

    private static final Map<Class<? extends IServiceProvider>, IServiceProvider> services;

    public static void registerService(final Class<? extends IServiceProvider> type, final IServiceProvider service){
        if (!ServiceProvider.services.containsKey(type)){
            ServiceProvider.services.put(type, service);
        }
    }

    @NotNull
    public static <T extends IServiceProvider> T getService(final Class<T> type){
        final IServiceProvider service = ServiceProvider.services.get(type);
        if (service == null){
            throw new NullPointerException();
        }
        return (T)service;
    }

    @NotNull
    @Deprecated
    public static Set<IUpdateable> getUpdateables(){
        final Set<IUpdateable> updateables = new HashSet<>();
        for (final IServiceProvider service : ServiceProvider.services.values()) {
            if (service instanceof IUpdateable) updateables.add((IUpdateable) service);
        }
        return updateables;
    }

    @Deprecated
    public static void updateUpdateables(){
        AtomicLong startTime = new AtomicLong();
        AtomicLong estimatedTime = new AtomicLong();
        getUpdateables().forEach(updateable -> {
            try {
                startTime.set(System.nanoTime());
                updateable.update();
                estimatedTime.set(System.nanoTime() - startTime.get());
                System.out.println("updated: " + updateable.getClass().getName() + " , " + estimatedTime+ "ns");
            }
            catch (final Exception ignored) {}
        });
    }

    @NotNull
    @Contract(" -> new")
    public static Set<IServiceProvider> getServices(){
        return new HashSet<>(ServiceProvider.services.values());
    }

    static {
        services = new HashMap<>();
    }

}
