package com.my.base.auth;

public class SubjectUtils {
	private static ThreadLocal<Subject> SUBJECT_THREAD_LOCAL = new ThreadLocal<>();
	
	public static void set(Subject subject) {
		SUBJECT_THREAD_LOCAL.set(subject);
	}
	
	public static Subject get() {
		return SUBJECT_THREAD_LOCAL.get();
	}
}
