package edu.cmu.mgmt.common;

public enum TopicType {

	STAR() {

		@Override
		public String toString() {
			return "Star";
		}
	},

	SCENE() {

		@Override
		public String toString() {
			return "Scene";
		}
	},

	CHARACTER() {

		@Override
		public String toString() {
			return "Character";
		}
	},
	
	STORY() {

		@Override
		public String toString() {
			return "Story";
		}
	},

	OTHER() {

		@Override
		public String toString() {
			return "Other";
		}
	};

	@Override
	public abstract String toString();
}
