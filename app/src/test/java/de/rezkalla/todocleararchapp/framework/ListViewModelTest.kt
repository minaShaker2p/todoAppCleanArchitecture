package de.rezkalla.todocleararchapp.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import de.rezkalla.core.data.Note
import de.rezkalla.core.usecase.GetAllNotesUseCase
import de.rezkalla.core.usecase.SortType
import de.rezkalla.todocleararchapp.TestCoroutineRule
import de.rezkalla.todocleararchapp.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var getAllNotesUseCase: GetAllNotesUseCase

    private lateinit var getAllNotesObserver: Observer<List<Note>>

    private lateinit var listViewModel: ListViewModel
    @Before
    fun setup() {
        getAllNotesUseCase = mock()

        getAllNotesObserver = mock()

        listViewModel = ListViewModel(getAllNotesUseCase).apply {
            notes.observeForever(getAllNotesObserver)
        }
    }

    @Test
    fun `should flow emit all notes from the repository`() = testCoroutineRule.runBlockingTest {

        // prepare mocked data for test
        val noteList = mutableListOf<Note>()
        val note = Note(
            "Unit testing ",
            "Kotlin Flow ",
            0L, 0L, 0L, 0
        )
        noteList.add(note)

        val notes = flow { emit(noteList) }

        // Given
        whenever(getAllNotesUseCase.invoke(SortType.DEC)).thenReturn(notes)

        // When
        val flow = listViewModel.getAllNotesUseCase.invoke(SortType.DEC)

        flow.test {
            assertEquals(expectItem(), noteList)
            expectComplete()
        }
    }
}