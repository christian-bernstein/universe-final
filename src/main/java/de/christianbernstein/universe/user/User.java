package de.christianbernstein.universe.user;

import de.christianbernstein.universe.admission.AdmissionContainer;
import de.christianbernstein.universe.admission.AdmissionGroup;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID uuid;

    private String systemName;

    private String displayName;

    private AdmissionContainer clientPermissions;

    private AdmissionGroup admissionGroup;

    public User(final UUID uuid, final String systemName, final String displayName){
        this.uuid = uuid;
        this.systemName = systemName;
        this.displayName = displayName;
    }

    public User(final UUID uuid, final String systemName){
        this.uuid = uuid;
        this.systemName = systemName;
        this.displayName = this.systemName;
    }

    public User(final UUID uuid) {
        this.uuid = uuid;
        this.systemName = Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName();
        this.displayName = this.systemName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAdmissionGroup(AdmissionGroup admissionGroup) {
        this.admissionGroup = admissionGroup;
    }

    public AdmissionGroup getAdmissionGroup() {
        return admissionGroup;
    }

    public AdmissionContainer getClientPermissions() {
        return clientPermissions;
    }
}
