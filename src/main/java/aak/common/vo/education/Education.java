package aak.common.vo.education;

import java.util.LinkedHashSet;
import java.util.Set;

import aak.common.vo.Org;

/*
 * 
 */
public class Education {

	private int seq;
	private String edu;

	private String type;

	private String start;

	private String end;

	private Org org;

	private ScoreDetailsVO score;

	private Set<String> specialization;
	private Set<String> subjects;

	public Education() {
		super();
	}

	public Education(int seq, String edu, String type) {
		this.seq = seq;
		this.edu = edu;
		this.type = type;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public ScoreDetailsVO getScore() {
		return score;
	}

	public void setScore(ScoreDetailsVO score) {
		this.score = score;
	}

	public Set<String> getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Set<String> specialization) {
		this.specialization = specialization;
	}

	public Set<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}

	public void addSpecialization(String recog) {
		if (specialization == null) {
			specialization = new LinkedHashSet<String>();
		}
		specialization.add(recog);
	}

	public void addSubject(String sub) {
		if (subjects == null) {
			specialization = new LinkedHashSet<String>();
		}
		subjects.add(sub);
	}

}
