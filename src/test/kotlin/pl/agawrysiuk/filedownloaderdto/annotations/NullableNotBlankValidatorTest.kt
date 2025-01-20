package pl.agawrysiuk.filedownloaderdto.annotations

import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NullableNotBlankValidatorTest {

    private val validatorFactory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private val validator: Validator = validatorFactory.validator

    data class TestClass(
        @field:NullableNotBlank
        val field: String?
    )

    @Test
    fun `When test null value Then no violations found`() {
        val invalidNullValue = TestClass(null)
        val violationsNull = validator.validate(invalidNullValue)
        assertTrue(violationsNull.isEmpty())  // null should pass validation
    }

    @Test
    fun `When test non-blank value Then no violations found`() {
        val validValue = TestClass("Valid String")
        val violationsValid = validator.validate(validValue)
        assertTrue(violationsValid.isEmpty())  // non-blank value should pass
    }

    @Test
    fun `When test empty string value Then violations found`() {
        val invalidEmptyValue = TestClass("")
        val violationsEmpty = validator.validate(invalidEmptyValue)
        assertTrue(violationsEmpty.isNotEmpty())  // empty string should fail validation
    }

    @Test
    fun `When test blank string value Then violations found`() {
        val invalidBlankValue = TestClass("   ")
        val violationsBlank = validator.validate(invalidBlankValue)
        assertTrue(violationsBlank.isNotEmpty())  // blank value should fail validation
    }
}
