/*
 * @(#) MetaSchemaTest.kt
 *
 * json-kotlin-schema Kotlin implementation of JSON Schema
 * Copyright (c) 2020 Peter Wall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.pwall.json.schema

import kotlin.test.Test

import java.io.File

import io.kstuff.test.shouldBe

import io.kjson.JSON
import io.kjson.JSONBoolean
import net.pwall.json.schema.parser.Parser

class MetaSchemaTest {

    private val parser = Parser { uri ->
        if (uri.toString().startsWith(metaSchemaPrefix)) {
            val localName = uri.toString().drop(metaSchemaPrefix.length).removeSuffix(".schema.json")
            File("src/test/resources/metaschema/${localName}.schema.json").inputStream()
        }
        else
            uri.toURL().openStream()
    }

    private val metaSchema = parser.parseURI(metaSchemaURI)

    @Test fun `should validate example schema against meta-schema`() {
        val exampleSchemaFile = File("src/test/resources/example.schema.json")
        val exampleSchema = JSON.parse(exampleSchemaFile.readText())
        metaSchema.validate(exampleSchema) shouldBe true
    }

    @Test fun `should validate person schema against meta-schema`() {
        val personSchemaFile = File("src/test/resources/test1/person/person.schema.json")
        val personSchema = JSON.parse(personSchemaFile.readText())
        metaSchema.validate(personSchema) shouldBe true
    }

    @Test fun `should validate utility schema against meta-schema`() {
        val utilitySchemaFile = File("src/test/resources/test1/utility.schema.json")
        val utilitySchema = JSON.parse(utilitySchemaFile.readText())
        metaSchema.validate(utilitySchema) shouldBe true
    }

    @Test fun `should validate empty schema against meta-schema`() {
        val emptySchemaFile = File("src/test/resources/empty.schema.json")
        val emptySchema = JSON.parse(emptySchemaFile.readText())
        metaSchema.validate(emptySchema) shouldBe true
    }

    @Test fun `should validate test-additional schema against meta-schema`() {
        val testAdditionalSchemaFile = File("src/test/resources/test-additional.schema.json")
        val testAdditionalSchema = JSON.parse(testAdditionalSchemaFile.readText())
        metaSchema.validate(testAdditionalSchema) shouldBe true
    }

    @Test fun `should validate test-additional-false schema against meta-schema`() {
        val testAdditionalFalseSchemaFile = File("src/test/resources/test-additional-false.schema.json")
        val testAdditionalFalseSchema = JSON.parse(testAdditionalFalseSchemaFile.readText())
        metaSchema.validate(testAdditionalFalseSchema) shouldBe true
    }

    @Test fun `should validate test-const schema against meta-schema`() {
        val testConstSchemaFile = File("src/test/resources/test-const.schema.json")
        val testConstSchema = JSON.parse(testConstSchemaFile.readText())
        metaSchema.validate(testConstSchema) shouldBe true
    }

    @Test fun `should validate test-enum schema against meta-schema`() {
        val testEnumSchemaFile = File("src/test/resources/test-enum.schema.json")
        val testEnumSchema = JSON.parse(testEnumSchemaFile.readText())
        metaSchema.validate(testEnumSchema) shouldBe true
    }

    @Test fun `should validate test-if-then-else schema against meta-schema`() {
        val testIfThenElseSchemaFile = File("src/test/resources/test-if-then-else.schema.json")
        val testIfThenElseSchema = JSON.parse(testIfThenElseSchemaFile.readText())
        metaSchema.validate(testIfThenElseSchema) shouldBe true
    }

    @Test fun `should validate test-item schema against meta-schema`() {
        val testItemSchemaFile = File("src/test/resources/test-item.schema.json")
        val testItemSchema = JSON.parse(testItemSchemaFile.readText())
        metaSchema.validate(testItemSchema) shouldBe true
    }

    @Test fun `should validate test-item-array schema against meta-schema`() {
        val testItemArraySchemaFile = File("src/test/resources/test-item-array.schema.json")
        val testItemArraySchema = JSON.parse(testItemArraySchemaFile.readText())
        metaSchema.validate(testItemArraySchema) shouldBe true
    }

    @Test fun `should validate test-not schema against meta-schema`() {
        val testNotSchemaFile = File("src/test/resources/test-not.schema.json")
        val testNotSchema = JSON.parse(testNotSchemaFile.readText())
        metaSchema.validate(testNotSchema) shouldBe true
    }

    @Test fun `should validate boolean value against meta-schema`() {
        metaSchema.validate(JSONBoolean.TRUE) shouldBe true
        metaSchema.validate(JSONBoolean.FALSE) shouldBe true
    }

    companion object {
        const val metaSchemaPrefix = "https://json-schema.org/draft/2019-09/"
        const val metaSchemaURI = "https://json-schema.org/draft/2019-09/schema"
    }

}
