package com.bae.service;

public class FreezerDoesntexistException extends Exception {

	@Override
	public String toString() {
		return "The freezer doesn't exist according this ID";
	}

}
