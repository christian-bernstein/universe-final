package de.christianbernstein.universe.admission;

import de.christianbernstein.universe.user.User;

public interface IAdmissionProvider {

    void loadAdmissions() throws Exception;
    void saveAdmissions() throws Exception;

    void registerAdmissionGroup(final AdmissionGroup admissionGroup) throws Exception;
    void deleteAdmissionGroup(final String systemName) throws Exception;

    AdmissionGroup getAdmissionGroup(final String systemName) throws Exception;
    AdmissionGroup getDefaultAdmissionGroup() throws Exception;

    AdmissionContainer getAdmissionContainer(final String systemName) throws Exception;

    void addGeneralAdmission(final Admission admission, final String systemName) throws Exception;
    void removeGeneralAdmission(final Admission admission, final String systemName) throws Exception;
    boolean hasGeneralAdmission(final Admission admission, final String systemName) throws Exception;

    void setAdmissionGroup(final String systemName, final User user) throws Exception;
    void addClientAdmission(final Admission admission, final User user) throws Exception;
    void removeClientAdmission(final Admission admission, final User user) throws Exception;
    boolean hasClientAdmission(final Admission admission, final User user) throws Exception;

}
