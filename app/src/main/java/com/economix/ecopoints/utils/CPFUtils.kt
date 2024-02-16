package com.economix.ecopoints.utils

import kotlin.random.Random

class CPFUtils {
    companion object {
        /**
         * Generates a random CPF (Brazilian individual taxpayer registry identification).
         * @param format indicates whether the CPF should be formatted or not.
         * @return a randomly generated CPF string.
         */
        fun generateRandomCPF(format: Boolean = true): String {
            val random = Random(System.currentTimeMillis())
            val cpfDigits = (1..9).map { random.nextInt(0, 9) }.toMutableList() // Generates the first 9 CPF digits

            // Calculates the first verification digit
            val dv1 = calculateCPFValidatorDigit(cpfDigits)
            cpfDigits.add(dv1)

            // Calculates the second verification digit
            val dv2 = calculateCPFValidatorDigit(cpfDigits)
            cpfDigits.add(dv2)

            val cpfGenerated = cpfDigits.joinToString("")
            return if (format) {
                formatCPF(cpfGenerated)
            } else {
                cpfGenerated
            }
        }

        /**
         * Validates a CPF.
         * @param cpf the CPF string to be validated.
         * @return true if the CPF is valid, false otherwise.
         */
        fun validateCPF(cpf: String): Boolean {
            val numbers = cpf.filter { it.isDigit() }.map { it.toString().toInt() }
            if (numbers.size != 11) return false

            val dv1 = calculateCPFValidatorDigit(numbers.subList(0, 9))
            val dv2 = calculateCPFValidatorDigit(numbers.subList(0, 10))

            return dv1 == numbers[9] && dv2 == numbers[10]
        }

        /**
         * Formats a CPF string.
         * @param cpf the CPF string to be formatted.
         * @return the formatted CPF string.
         */
        fun formatCPF(cpf: String): String {
            return cpf.replace(Regex("(\\d{3})(\\d{3})(\\d{3})(\\d{2})"), "$1.$2.$3-$4")
        }

        /**
         * Calculates the verification digit for a CPF based on the provided digits.
         * @param cpfDigits the CPF digits excluding the verification digits.
         * @return the calculated verification digit.
         */
        private fun calculateCPFValidatorDigit(cpfDigits: List<Int>): Int {
            val products = (cpfDigits.indices).map { cpfDigits[it] * (cpfDigits.size + 1 - it) }
            val sum = products.sum()
            val remainder = sum % 11
            return if (remainder < 2) 0 else 11 - remainder
        }
    }
}
