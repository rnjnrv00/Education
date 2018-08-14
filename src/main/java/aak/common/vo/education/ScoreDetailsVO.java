package aak.common.vo.education;

import java.util.LinkedHashSet;
import java.util.Set;

/*
 * 
 */
public class ScoreDetailsVO {
	private float score;
	private String scale;
	Set<String> recognition;

	ScoreDetailsVO() {
		super();
	}

	ScoreDetailsVO(float score, String scale) {
		this.score = score;
		this.scale = scale;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Set<String> getRecognition() {
		return recognition;
	}

	public void setRecognition(Set<String> recognition) {
		this.recognition = recognition;
	}

	public void addRecognition(String recog) {
		if (recognition == null) {
			recognition = new LinkedHashSet<String>();
		}
		recognition.add(recog);
	}
}
