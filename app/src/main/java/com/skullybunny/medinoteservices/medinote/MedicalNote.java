package com.skullybunny.medinoteservices.medinote;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by WWW on 18-Mar-17.
 */

public class MedicalNote {

    @SerializedName("Id")
    private int id;

    @SerializedName("DoctorID")
    private int doctorID;

    @SerializedName("StudentID")
    private int studentID;

    @SerializedName("InstitutionID")
    private int institutionID;

    @SerializedName("HealthcareFacilityId")
    private int healthcareFacilityId;

    @SerializedName("FacilityName")
    private String facilityName;

    @SerializedName("StudentName")
    private String studentName;

    @SerializedName("StudentAddress")
    private String studentAddress;

    @SerializedName("StudentAge")
    private int studentAge;

    @SerializedName("Needs")
    private String needs;

    @SerializedName("InstitutionName")
    private String institutionName;

    @SerializedName("DoctorName")
    private String doctorName;

    @SerializedName("DoctorPosition")
    private String doctorPosition;

    @SerializedName("VisitDate")
    private String date;

    @SerializedName("Diagnose")
    private String diagnose;

    @SerializedName("MEN")
    private String men;

    public MedicalNote()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(int institutionID) {
        this.institutionID = institutionID;
    }

    public int getHealthcareFacilityId() {
        return healthcareFacilityId;
    }

    public void setHealthcareFacilityId(int healthcareFacilityId) {
        this.healthcareFacilityId = healthcareFacilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPosition() {
        return doctorPosition;
    }

    public void setDoctorPosition(String doctorPosition) {
        this.doctorPosition = doctorPosition;
    }

    public String getVisitDate() {
        return date;
    }

    public void setVisitDate(String visitDate) {
        this.date = visitDate;
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
