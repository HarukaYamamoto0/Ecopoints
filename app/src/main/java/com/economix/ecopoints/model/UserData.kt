package com.economix.ecopoints.model

data class UserData(
	val firstName: String,
	val lastName: String,
	val cpf: String,
	val avatarUrl: String,
	val wallet: Int
)