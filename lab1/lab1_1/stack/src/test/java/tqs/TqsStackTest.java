package tqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

public class TqsStackTest {

    private TqsStack stack;
    private TqsStack bounded_stack;

    // ////////////////////////////////////////////////////////////////////

    @BeforeEach
    public void start(){
        this.stack = new TqsStack();
        this.bounded_stack = new TqsStack(3);
    }

    @AfterEach
    public void end(){
        stack.removeElements();
        bounded_stack.removeElements();
    }

    // ////////////////////////////////////////////////////////////////////

    @DisplayName("a)")
    @Test
    public void isEmpty() {
        assertEquals( true, this.stack.isEmpty() );
    }

    @DisplayName("b)")
    @Test
    public void size() {
        assertEquals( 0, this.stack.size() );
    }

    @DisplayName("c)")
    @Test
    public void push() {
        this.stack.push("uno");
        this.stack.push("dos");
        this.stack.push("tres");

        assertEquals( 3, this.stack.size() );
        assertNotEquals( true, this.stack.isEmpty() );
    }

    @DisplayName("d)")
    @Test
    public void pop() {
        this.stack.push("quatro");
        Object popped_value = this.stack.pop();

        assertEquals( "quatro", popped_value );
        assertEquals( 0, this.stack.size() );
        assertEquals( true, this.stack.isEmpty() );
    }


    @DisplayName("e)")
    @Test
    public void peek() {
        this.stack.push("cinco");
        Object peeked_value = this.stack.peek();
        
        assertEquals( "cinco", peeked_value );
        assertEquals( 1, this.stack.size() );
        assertEquals( false, this.stack.isEmpty() );
    }

    @DisplayName("f)")
    @Test
    public void multiplePops() {
        this.stack.push("uno");
        this.stack.push("dos");

        this.stack.pop();
        this.stack.pop();

        assertEquals( 0, this.stack.size() );
    }

    @DisplayName("g)")
    @Test
    public void Pop_NoSuchElementException() {
        assertThrows( NoSuchElementException.class, () -> { this.stack.pop(); } );
    }

    @DisplayName("h)")
    @Test
    public void Peek_NoSuchElementException() {
        assertThrows( NoSuchElementException.class, () -> { this.stack.peek(); } );
    }


    @DisplayName("i)")
    @Test
    public void BoundedStack_NoSuchElementException() {
        this.bounded_stack.push("um");
        this.bounded_stack.push("dois");
        this.bounded_stack.push("três");
        
        assertThrows( IllegalStateException.class, () -> { this.bounded_stack.push("quatro"); } );
    }


}
