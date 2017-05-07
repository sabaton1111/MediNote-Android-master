package com.skullybunny.medinoteservices.medinote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin on 4/21/2017.
 */

public class MedicalNoteAddDTO
{
    @SerializedName("DoctorNIN")
    private String doctorNIN;

    @SerializedName("StudentNIN")
    private String studentNIN;

    @SerializedName("Needs")
    private String needs;

    @SerializedName("InstitutionName")
    private String institutionName;

    @SerializedName("VisitDate")
    private String visitDate;

    @SerializedName("Diagnose")
    private String diagnose;

    @SerializedName("MEN")
    private String men;

    public MedicalNoteAddDTO()
    {

    }

    public MedicalNoteAddDTO(String doctorNIN, String studentNIN, String needs, String institutionName,
                             String visitDate, String diagnose, String men)
    {
        this.doctorNIN = doctorNIN;
        this.studentNIN = studentNIN;
        this.needs = needs;
        this.institutionName = institutionName;
        this.visitDate = visitDate;
        this.diagnose = diagnose;
        this.men = men;
    }

    public String getDoctorNIN() {
        return doctorNIN;
    }

    public void setDoctorNIN(String doctorNIN) {
        this.doctorNIN = doctorNIN;
    }

    public String getStudentNIN() {
        return studentNIN;
    }

    public void setStudentNIN(String studentNIN) {
        this.studentNIN = studentNIN;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }
}
