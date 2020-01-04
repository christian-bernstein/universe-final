package de.christianbernstein.universe.admission;

import com.google.gson.reflect.TypeToken;
import de.christianbernstein.universe.provider.IServiceProvider;
import de.christianbernstein.universe.user.User;
import de.christianbernstein.universe.util.GsonUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AdmissionProvider implements IAdmissionProvider, IServiceProvider {

    private List<AdmissionGroup> groupRegister;
    private final Type type;
    private final File file;

    public AdmissionProvider(final File file) throws Exception {
        this.file = file;
        this.loadAdmissions();
    }

    @Override
    public void loadAdmissions() throws Exception {
        try (final FileReader fileReader = new FileReader(this.file)) {
            this.groupRegister = GsonUtil.getGson().fromJson(fileReader, type);
        }catch (final Exception ex){
            throw ex;
        }
    }

    @Override
    public void saveAdmissions() throws Exception {
        try (final FileWriter fileWriter = new FileWriter(this.file)) {
            GsonUtil.getGson().toJson(this.groupRegister, fileWriter);
        }catch (final Exception ex){
            throw ex;
        }
    }

    @Override
    public void registerAdmissionGroup(AdmissionGroup admissionGroup) throws Exception {
        if (this.groupRegister.contains(admissionGroup)){
            throw new ArrayStoreException();
        }
        this.groupRegister.add(admissionGroup);
    }

    @Override
    public void deleteAdmissionGroup(String systemName) throws Exception {
        final AdmissionGroup admissionGroup = this.getAdmissionGroup(systemName);
        if (!this.groupRegister.contains(admissionGroup)){
            throw new NullPointerException();
        }
        this.groupRegister.remove(admissionGroup);
    }

    @Override
    public AdmissionGroup getAdmissionGroup(String systemName) throws Exception {
        for (final AdmissionGroup admissionGroup : this.groupRegister) {
            if (admissionGroup.getSystemName().equals(systemName)){
                return admissionGroup;
            }
        }
        throw new NullPointerException();
    }

    @Override
    public AdmissionGroup getDefaultAdmissionGroup() throws Exception {
        for (final AdmissionGroup admissionGroup : this.groupRegister) {
            if (admissionGroup.isDefaultGroup()) return admissionGroup;
        }
        throw new NullPointerException();
    }

    @Override
    public AdmissionContainer getAdmissionContainer(String systemName) throws Exception {
        return this.getAdmissionGroup(systemName).getContainer();
    }

    @Override
    public void addGeneralAdmission(Admission admission, String systemName) throws Exception {
        final AdmissionGroup admissionGroup = this.getAdmissionGroup(systemName);
        admissionGroup.getContainer().add(admission);
    }

    @Override
    public void removeGeneralAdmission(Admission admission, String systemName) throws Exception {
        final AdmissionGroup admissionGroup = this.getAdmissionGroup(systemName);
        admissionGroup.getContainer().remove(admission);
    }

    @Override
    public boolean hasGeneralAdmission(Admission admission, String systemName) throws Exception {
        final AdmissionGroup admissionGroup = this.getAdmissionGroup(systemName);
        return admissionGroup.getContainer().hasAdmission(admission);
    }

    @Override
    public void setAdmissionGroup(String systemName, @NotNull User user) throws Exception {
        final AdmissionGroup admissionGroup = this.getAdmissionGroup(systemName);
        user.setAdmissionGroup(admissionGroup);
    }

    @Override
    public void addClientAdmission(Admission admission, @NotNull User user) throws Exception {
        user.getClientPermissions().add(admission);
    }

    @Override
    public void removeClientAdmission(Admission admission, @NotNull User user) throws Exception {
        user.getClientPermissions().remove(admission);
    }

    @Override
    public boolean hasClientAdmission(Admission admission, @NotNull User user) throws Exception {
        if (this.hasGeneralAdmission(admission, user.getAdmissionGroup().getSystemName())) return true;
        return user.getClientPermissions().hasAdmission(admission);
    }

    {
        this.groupRegister = new ArrayList<>();
        this.type = new TypeToken<ArrayList<AdmissionGroup>>(){}.getType();
    }
}
