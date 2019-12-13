package com.bae.service;

public class itemDoesntexistException extends Exception {

	@Override
	public String toString() {
		return "The item doesn't exist according this ID";
	}

}
