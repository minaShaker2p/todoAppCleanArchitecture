package de.rezkalla.todocleararchapp.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import de.rezkalla.core.data.Note
import de.rezkalla.core.usecase.AddNoteUseCase
import de.rezkalla.core.usecase.GetNoteUseCase
import de.rezkalla.core.usecase.RemoveNoteUseCase
import de.rezkalla.todocleararchapp.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NoteViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var noteViewModel: NoteViewModel

    @Mock
    private lateinit var addNoteUseCases: AddNoteUseCase

    @Mock
     lateinit var removeNoteUseCaseUseCase: RemoveNoteUseCase

    @Mock
    private lateinit var getNoteUseCase: GetNoteUseCase

    @Mock
    private lateinit var savedNoteObserver: Observer<Boolean>

    @Mock
    private lateinit var getNoteObserver: Observer<Note>

    @Mock
    private lateinit var deletedNoteObserver: Observer<Boolean>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        noteViewModel = NoteViewModel(
            addNoteUseCases,
            removeNoteUseCaseUseCase,
            getNoteUseCase
        ).apply {
            saved.observeForever(savedNoteObserver)

            note.observeForever(getNoteObserver)

            deleted.observeForever(deletedNoteObserver)
        }
    }


    @Test
    fun `should saved observer changed to true when add new note`() =
        testCoroutineRule.runBlockingTest {
           // Given
            val note = Note("unit test",
                "unit test content",
                0L,
                0L,
                0,
                0)

            whenever(addNoteUseCases.invoke(note)).thenReturn(Unit)

            // when
            noteViewModel.saveNote(note)

            advanceTimeBy(500)

            // Then
            verify(savedNoteObserver).onChanged(true)
        }

    @Test
    fun `should note observer post note when get note by id `() = testCoroutineRule.runBlockingTest{
        // Given
        val data= any<Note>()
        whenever(getNoteUseCase.invoke(0)).thenReturn(data)

        // when
        noteViewModel.getNote(0)

        // then
        verify(getNoteObserver, times(1)).onChanged(data)
    }

    @Test
    fun `should deleted observer changed to true when delete  note`() =
        testCoroutineRule.runBlockingTest {
            //Given
            val note = Note("unit test",
                "unit test content",
                0L,
                0L,
                0,
                0)

            whenever(removeNoteUseCaseUseCase.invoke(note)).thenReturn(Unit)

            // when
            noteViewModel.removeNote(note)


            //Then
            verify(deletedNoteObserver, times(1)).onChanged(true)

        }

}