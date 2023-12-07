package com.example.XyhubEmployee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubEmployee.basedata.model.AuditModel;
import com.example.XyhubEmployee.idgenerator.IdGenerator;
import com.example.XyhubEmployee.idgenerator.IdPrefix;




@Entity
@Table(name = "employee_permission")
public class EmployeePermission extends AuditModel{
	
	@Id
	@IdPrefix(value = "PER_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubEmployee.idgenerator.IdGenerator")
	@Column(name = "id")
	private String Id;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "hr_calendar_access", nullable = false, columnDefinition = "boolean default false")
	private boolean hrCalenderAccess;
	
	@Column(name = "asset_mngt_required" , columnDefinition = "boolean default false") 
	private boolean assetMngtAccess;
	
	@Column(name = "vendor_access" , columnDefinition = "boolean default false") 
	private boolean vendorAccess;

	@Column(name = "job_admin", nullable = false, columnDefinition = "boolean default false")
	private boolean jobAdmin;
	
	@Column(name = "job_opening_add" , columnDefinition = "boolean default false") 
	private boolean jobOpeningAdd;
	
	@Column(name = "job_openings_view" , columnDefinition = "boolean default true")
	private boolean jobOpeningsView;
	
	@Column(name = "job_app_upload" , columnDefinition = "boolean default true")
	private boolean jobAppUpload;
	
	@Column(name = "job_app_view_all" , columnDefinition = "boolean default false")
	private boolean jobAppViewAll;
	
	@Column(name = "job_off_view_all" , columnDefinition = "boolean default false")
	private boolean jobOffViewAll;
	
	@Column(name = "job_int_view_all" , columnDefinition = "boolean default false")
	private boolean jobIntViewAll;
	
	@Column(name = "hr_cal_schedule_add" , columnDefinition = "boolean default false")
	private boolean hrCalScheduleAdd;
	
	@Column(name = "hr_cal_view_all" , columnDefinition = "boolean default false")
	private boolean hrCalViewAll;
	
	@Column(name = "ann_view_all" , columnDefinition = "boolean default true")
	private boolean annViewAll;
	
	@Column(name = "ann_edit_all" , columnDefinition = "boolean default false")
	private boolean annEditAll;
	
	@Column(name = "ann_add" , columnDefinition = "boolean default false")
	private boolean annAdd;
	
	@Column(name = "art_view_all" , columnDefinition = "boolean default true")
	private boolean artViewAll;
	
	@Column(name = "art_edit_all" , columnDefinition = "boolean default false")
	private boolean artEditAll;
	
	@Column(name = "art_add" , columnDefinition = "boolean default false")
	private boolean artAdd;
	
	@Column(name = "tkt_admin" , columnDefinition = "boolean default false")
	private boolean tktAdmin;
	
	@Column(name = "tkt_view_self" , columnDefinition = "boolean default true")
	private boolean tktViewSelf;
	
	@Column(name = "tkt_add" , columnDefinition = "boolean default true")
	private boolean tktAdd;
	
	@Column(name = "tkt_assign" , columnDefinition = "boolean default false")
	private boolean tktAssign;
	
	@Column(name = "prj_admin" , columnDefinition = "boolean default false")
	private boolean prjAdmin;

	@Column(name = "prj_view_all" , columnDefinition = "boolean default false")
	private boolean prjViewAll;
	
	@Column(name = "prj_edit_all" , columnDefinition = "boolean default false")
	private boolean prjEditAll;
	
	@Column(name = "prj_add" , columnDefinition = "boolean default false")
	private boolean prjAdd;
	
	@Column(name = "timesheet_add" , columnDefinition = "boolean default true")
	private boolean timesheetAdd;
	
	@Column(name = "timesheet_admin" , columnDefinition = "boolean default false")
	private boolean timesheetAdmin;
	
	@Column(name = "asst_admin" , columnDefinition = "boolean default false")
	private boolean asstAdmin;
	
	@Column(name = "asst_view_all" , columnDefinition = "boolean default true")
	private boolean asstViewAll;
	
	@Column(name = "asst_add" , columnDefinition = "boolean default false")
	private boolean asstAdd;
	
	@Column(name = "asst_iss_add" , columnDefinition = "boolean default false")
	private boolean asstIssAdd;
	
	@Column(name = "asst_bill_add" , columnDefinition = "boolean default false")
	private boolean asstBillAdd;
	
	@Column(name = "jv_view_all" , columnDefinition = "boolean default false")
	private boolean jvViewAll;
	
	@Column(name = "av_view_all" , columnDefinition = "boolean default false")
	private boolean avViewAll;
	
	@Column(name = "iss_track_admin" , columnDefinition = "boolean default false")
	private boolean issTrackAdmin;
	
	@Column(name = "iss_track_view_all" , columnDefinition = "boolean default false")
	private boolean issTrackViewAll;
	
	@Column(name = "emp_admin" , columnDefinition = "boolean default false")
	private boolean empAdmin;
	
	@Column(name = "emp_view_all" , columnDefinition = "boolean default true")
	private boolean empViewAll;
	
//	@Column(name = "emp_view_sal",columnDefinition = "boolean default false")
//	private boolean empViewSal;
//	
	@Column(name = "job_rcrt_mng",columnDefinition = "boolean default false")
	private boolean jobRcrtMng;
	
	@Column(name = "emp_doc_add",columnDefinition = "boolean default false")
	private boolean empDocAdd;
	
	@Column(name = "emp_login_his",columnDefinition = "boolean default false")
	private boolean empLoginHis;
	
	@Column(name = "training_admin",columnDefinition = "boolean default false")
	private boolean trainingAdmin;

	
	@Column(name = "stationery_admin" , columnDefinition = "boolean default false")
	private boolean stationeryAdmin;
	
	@Column(name = "stationery_view_all" , columnDefinition = "boolean default false")
	private boolean stationeryViewAll;
	
	@Column(name = "stationery_add" , columnDefinition = "boolean default false")
	private boolean stationeryAdd;	
	
	@Column(name = "qms_admin" , columnDefinition = "boolean default false")
	private boolean qmsAdmin;
	
	@Column(name = "qms_view_all" , columnDefinition = "boolean default false")
	private boolean qmsViewAll;
	
	@Column(name = "po_admin" , columnDefinition = "boolean default false")
	private boolean poAdmin;
	
	@Column(name = "po_add" , columnDefinition = "boolean default false")
	private boolean poAdd;
	
	@Column(name = "po_edit" , columnDefinition = "boolean default false")
	private boolean poEdit;
	
	@Column(name = "po_mgmt" , columnDefinition = "boolean default false")
	private boolean poMgmt;
	
	@Column(name = "po_view_all" ,columnDefinition = "boolean default false")
	private boolean poViewAll;
	
	@Column(name = "ven_add" ,columnDefinition = "boolean default false")
	private boolean venAdd;
	
	@Column(name = "ven_edit" ,columnDefinition = "boolean default false")
	private boolean venEdit;
	
	@Column(name = "ven_view_all" ,columnDefinition = "boolean default false")
	private boolean venViewAll;
	
	@Column(name = "ven_admin" ,columnDefinition = "boolean default false")
	private boolean venAdmin;
	
//	@Column(name = "bil_admin" ,columnDefinition = "boolean default false")
//	private boolean billAdmin;
//	
//	@Column(name = "bil_add" ,columnDefinition = "boolean default false")
//	private boolean billAdd;
//	
//	@Column(name = "bill_edit" ,columnDefinition = "boolean default false" )
//	private boolean billEdit;
//
//	
//	@Column(name = "bill_view_all" ,columnDefinition = "boolean default false")
//	private boolean billView;
	
	@Column(name = "edit_admin" ,columnDefinition = "boolean default false")
	private boolean editAdmin;
	
	public boolean isStationeryAdmin() {
		return stationeryAdmin;
	}


	public boolean isPoViewAll() {
		return poViewAll;
	}

	public void setPoViewAll(boolean poViewAll) {
		this.poViewAll = poViewAll;
	}

	public void setStationeryAdmin(boolean stationeryAdmin) {
		this.stationeryAdmin = stationeryAdmin;
	}

	public boolean isStationeryViewAll() {
		return stationeryViewAll;
	}

	public void setStationeryViewAll(boolean stationeryViewAll) {
		this.stationeryViewAll = stationeryViewAll;
	}

	public boolean isStationeryAdd() {
		return stationeryAdd;
	}

	public void setStationeryAdd(boolean stationeryAdd) {
		this.stationeryAdd = stationeryAdd;
	}

	public EmployeePermission(){
		this.jobOpeningsView = true;
		this.jobAppUpload = true;
		this.annViewAll = true;
		this.artViewAll = true;
		this.timesheetAdd = true;
		this.artAdd = true;
		this.empViewAll = true;
		this.tktViewSelf = true;
		this.tktAdd = true;
	}
	
	public boolean isPrjAdmin() {
		return prjAdmin;
	}

	public void setPrjAdmin(boolean prjAdmin) {
		this.prjAdmin = prjAdmin;
	}
	
	public boolean isVendorAccess() {
		return vendorAccess;
	}

	public void setVendorAccess(boolean vendorAccess) {
		this.vendorAccess = vendorAccess;
	}

	public boolean isHrCalenderAccess() {
		return hrCalenderAccess;
	}

	public void setHrCalenderAccess(boolean hrCalenderAccess) {
		this.hrCalenderAccess = hrCalenderAccess;
	}

	public boolean isAssetMngtAccess() {
		return assetMngtAccess;
	}

	public void setAssetMngtAccess(boolean assetMngtAccess) {
		this.assetMngtAccess = assetMngtAccess;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmpPermissionId() {
		return Id;
	}

	public void setEmpPermissionId(String empPermissionId) {
		this.Id = empPermissionId;
	}

	public boolean getEmpAdmin() {
		return empAdmin;
	}

	public void setEmpAdmin(boolean empAdmin) {
		this.empAdmin = empAdmin;
	}

	public boolean getJobAdmin() {
		return jobAdmin;
	}

	public void setJobAdmin(boolean jobAdmin) {
		this.jobAdmin = jobAdmin;
	}

	public boolean getJobOpeningAdd() {
		return jobOpeningAdd;
	}

	public void setJobOpeningAdd(boolean jobOpeningAdd) {
		this.jobOpeningAdd = jobOpeningAdd;
	}

	public boolean getJobOpeningsView() {
		return jobOpeningsView;
	}

	public void setJobOpeningsView(boolean jobOpeningsView) {
		this.jobOpeningsView = jobOpeningsView;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public boolean getJobAppUpload() {
		return jobAppUpload;
	}

	public void setJobAppUpload(boolean jobAppUpload) {
		this.jobAppUpload = jobAppUpload;
	}

	public boolean getJobAppViewAll() {
		return jobAppViewAll;
	}

	public void setJobAppViewAll(boolean jobAppViewAll) {
		this.jobAppViewAll = jobAppViewAll;
	}

	public boolean getJobOffViewAll() {
		return jobOffViewAll;
	}

	public void setJobOffViewAll(boolean jobOffViewAll) {
		this.jobOffViewAll = jobOffViewAll;
	}

	public boolean getJobIntViewAll() {
		return jobIntViewAll;
	}

	public void setJobIntViewAll(boolean jobIntViewAll) {
		this.jobIntViewAll = jobIntViewAll;
	}

	public boolean getHrCalScheduleAdd() {
		return hrCalScheduleAdd;
	}

	public void setHrCalScheduleAdd(boolean hrCalScheduleAdd) {
		this.hrCalScheduleAdd = hrCalScheduleAdd;
	}

	public boolean getHrCalViewAll() {
		return hrCalViewAll;
	}

	public void setHrCalViewAll(boolean hrCalViewAll) {
		this.hrCalViewAll = hrCalViewAll;
	}

	public boolean getAnnViewAll() {
		return annViewAll;
	}

	public void setAnnViewAll(boolean annViewAll) {
		this.annViewAll = annViewAll;
	}

	public boolean getAnnEditAll() {
		return annEditAll;
	}

	public void setAnnEditAll(boolean annEditAll) {
		this.annEditAll = annEditAll;
	}

	public boolean getAnnAdd() {
		return annAdd;
	}

	public void setAnnAdd(boolean annAdd) {
		this.annAdd = annAdd;
	}

	public boolean getArtViewAll() {
		return artViewAll;
	}

	public void setArtViewAll(boolean artViewAll) {
		this.artViewAll = artViewAll;
	}

	public boolean getArtEditAll() {
		return artEditAll;
	}

	public void setArtEditAll(boolean artEditAll) {
		this.artEditAll = artEditAll;
	}

	public boolean getArtAdd() {
		return artAdd;
	}

	public void setArtAdd(boolean artAdd) {
		this.artAdd = artAdd;
	}

	public boolean getTktAdmin() {
		return tktAdmin;
	}

	public void setTktAdmin(boolean tktAdmin) {
		this.tktAdmin = tktAdmin;
	}

	public boolean getTktAdd() {
		return tktAdd;
	}

	public void setTktAdd(boolean tktAdd) {
		this.tktAdd = tktAdd;
	}

	public boolean getTktAssign() {
		return tktAssign;
	}

	public void setTktAssign(boolean tktAssign) {
		this.tktAssign = tktAssign;
	}

	public boolean getPrjViewAll() {
		return prjViewAll;
	}

	public void setPrjViewAll(boolean prjViewAll) {
		this.prjViewAll = prjViewAll;
	}

	public boolean getPrjEditAll() {
		return prjEditAll;
	}

	public void setPrjEditAll(boolean prjEditAll) {
		this.prjEditAll = prjEditAll;
	}

	public boolean getPrjAdd() {
		return prjAdd;
	}

	public void setPrjAdd(boolean prjAdd) {
		this.prjAdd = prjAdd;
	}

	public boolean getTimesheetAdd() {
		return timesheetAdd;
	}

	public void setTimesheetAdd(boolean timesheetAdd) {
		this.timesheetAdd = timesheetAdd;
	}

	public boolean getTimesheetAdmin() {
		return timesheetAdmin;
	}

	public void setTimesheetAdmin(boolean timesheetAdmin) {
		this.timesheetAdmin = timesheetAdmin;
	}

	public boolean getAsstAdmin() {
		return asstAdmin;
	}

	public void setAsstAdmin(boolean asstAdmin) {
		this.asstAdmin = asstAdmin;
	}

	public boolean getAsstAdd() {
		return asstAdd;
	}

	public void setAsstAdd(boolean asstAdd) {
		this.asstAdd = asstAdd;
	}

	public boolean getAsstIssAdd() {
		return asstIssAdd;
	}

	public void setAsstIssAdd(boolean asstIssAdd) {
		this.asstIssAdd = asstIssAdd;
	}

	public boolean getAsstBillAdd() {
		return asstBillAdd;
	}

	public void setAsstBillAdd(boolean asstBillAdd) {
		this.asstBillAdd = asstBillAdd;
	}

	public boolean getJvViewAll() {
		return jvViewAll;
	}

	public void setJvViewAll(boolean jvViewAll) {
		this.jvViewAll = jvViewAll;
	}

	public boolean getAvViewAll() {
		return avViewAll;
	}

	public void setAvViewAll(boolean avViewAll) {
		this.avViewAll = avViewAll;
	}

	public boolean getIssTrackAdmin() {
		return issTrackAdmin;
	}

	public void setIssTrackAdmin(boolean issTrackAdmin) {
		this.issTrackAdmin = issTrackAdmin;
	}

	public boolean getIssTrackViewAll() {
		return issTrackViewAll;
	}

	public void setIssTrackViewAll(boolean issTrackViewAll) {
		this.issTrackViewAll = issTrackViewAll;
	}

	public boolean isTktViewSelf() {
		return tktViewSelf;
	}

	public void setTktViewSelf(boolean tktViewSelf) {
		this.tktViewSelf = tktViewSelf;
	}

	public boolean isAsstViewAll() {
		return asstViewAll;
	}

	public void setAsstViewAll(boolean asstViewAll) {
		this.asstViewAll = asstViewAll;
	}

	public boolean isEmpViewAll() {
		return empViewAll;
	}

	public void setEmpViewAll(boolean empViewAll) {
		this.empViewAll = empViewAll;
	}

//	public boolean isEmpViewSal() {
//		return empViewSal;
//	}
//
//	public void setEmpViewSal(boolean empViewSal) {
//		this.empViewSal = empViewSal;
//	}

	public boolean isJobRcrtMng() {
		return jobRcrtMng;
	}

	public void setJobRcrtMng(boolean jobRcrtMng) {
		this.jobRcrtMng = jobRcrtMng;
	}

	public boolean isEmpDocAdd() {
		return empDocAdd;
	}

	public void setEmpDocAdd(boolean empDocAdd) {
		this.empDocAdd = empDocAdd;
	}

	public boolean isEmpLoginHis() {
		return empLoginHis;
	}

	public void setEmpLoginHis(boolean empLoginHis) {
		this.empLoginHis = empLoginHis;
	}
	
	public boolean isTrainingAdmin() {
		return trainingAdmin;
	}

	public void setTrainingAdmin(boolean trainingAdmin) {
		this.trainingAdmin = trainingAdmin;
	}

	public boolean isQmsAdmin() {
		return qmsAdmin;
	}

	public void setQmsAdmin(boolean qmsAdmin) {
		this.qmsAdmin = qmsAdmin;
	}

	public boolean isQmsViewAll() {
		return qmsViewAll;
	}

	public void setQmsViewAll(boolean qmsViewAll) {
		this.qmsViewAll = qmsViewAll;
	}

	public boolean isPoAdmin() {
		return poAdmin;
	}

	public void setPoAdmin(boolean poAdmin) {
		this.poAdmin = poAdmin;
	}

	public boolean isPoAdd() {
		return poAdd;
	}

	public void setPoAdd(boolean poAdd) {
		this.poAdd = poAdd;
	}

	public boolean isPoEdit() {
		return poEdit;
	}

	public void setPoEdit(boolean poEdit) {
		this.poEdit = poEdit;
	}

	public boolean isPoMgmt() {
		return poMgmt;
	}

	public void setPoMgmt(boolean poMgmt) {
		this.poMgmt = poMgmt;
	}

	public boolean isVenAdd() {
		return venAdd;
	}

	public void setVenAdd(boolean venAdd) {
		this.venAdd = venAdd;
	}

	public boolean isVenEdit() {
		return venEdit;
	}

	public void setVenEdit(boolean venEdit) {
		this.venEdit = venEdit;
	}

	public boolean isVenViewAll() {
		return venViewAll;
	}

	public void setVenViewAll(boolean venViewAll) {
		this.venViewAll = venViewAll;
	}

	public boolean isVenAdmin() {
		return venAdmin;
	}

//	public boolean isBillAdmin() {
//		return billAdmin;
//	}
//
//	public void setBillAdmin(boolean billAdmin) {
//		this.billAdmin = billAdmin;
//	}
//
//	public boolean isBillAdd() {
//		return billAdd;
//	}
//
//	public void setBillAdd(boolean billAdd) {
//		this.billAdd = billAdd;
//	}
//
//	public boolean isBillEdit() {
//		return billEdit;
//	}
//
//	public void setBillEdit(boolean billEdit) {
//		this.billEdit = billEdit;
//	}
//
//	public boolean isBillView() {
//		return billView;
//	}
//
//	public void setBillView(boolean billView) {
//		this.billView = billView;
//	}

	public void setVenAdmin(boolean venAdmin) {
		this.venAdmin = venAdmin;
	}


	public boolean isEditAdmin() {
		return editAdmin;
	}

	public void setEditAdmin(boolean editAdmin) {
		this.editAdmin = editAdmin;
	}
}

	
	
	
	
	
	
	
	
