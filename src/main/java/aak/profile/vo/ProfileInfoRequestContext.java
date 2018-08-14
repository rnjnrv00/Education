package aak.profile.vo;



public class ProfileInfoRequestContext {
	    // "who" is requesting to view/get education details
		private String who;
		// "whose" education details requested
		private String whose;
		// how "who" is linked/connected to "whose" 
		private String link;
		// requested data for "which" ASPECT/Sexon/LABEL/node/category/
		private String which;
		//  who is searching whose for what purpose
		private String context;
		
		
		
		public String getContext() {
			return context;
		}
		public void setContext(String context) {
			this.context = context;
		}
		public String getWho() {
			return who;
		}
		public void setWho(String who) {
			this.who = who;
		}
		public String getWhose() {
			return whose;
		}
		public void setWhose(String whose) {
			this.whose = whose;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getWhich() {
			return which;
		}
		public void setWhich(String which) {
			this.which = which;
		}
		
}
