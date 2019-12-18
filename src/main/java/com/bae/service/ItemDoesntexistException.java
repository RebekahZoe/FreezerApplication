package com.bae.service;

public class ItemDoesntexistException extends Exception {

	@Override
	public String toString() {
		return "The item doesn't exist according this ID";
	}

}
