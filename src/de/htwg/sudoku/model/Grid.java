package de.htwg.sudoku.model;

import java.util.BitSet;
import java.util.Random;

/**
 * The Grid is the playing field of a Sudoku puzzle.
 * It consists of Cells.
 * Cells are organized in Houses.
 * The Grid has a size, which is the number of cells in a row or column. Size must be 1, 4, or 9.
 */
public class Grid {

/* Fields */
    private final static int MAXSIZE = 9;

    private int size;
    private int blockSize;


    private Cell[][] cells;
    private House[] rows;
    private House[] columns;
    private House[] blocks;

/* Constructors */
    public Grid(int size) throws IllegalArgumentException{
        if (size < 1 || MAXSIZE < size) {
            throw new IllegalArgumentException(
                    "size must be between 1 and " + MAXSIZE);
        }
       if (!isSquareOfNaturalNumber(size)) {
            throw new IllegalArgumentException(
                    "size must be a square of a natural number");
        }
        setSize(size);
        setBlockSize(blocksPerEdge(size));

        // create Cell and Houses
        cells = new Cell[getSize()][getSize()];
        rows = new House[getSize()];
        columns = new House[getSize()];
        blocks = new House[getSize()];

        // initialize Houses, connect them to their cells.
        for (int index = 0; index < getSize(); index++) {
            rows[index] = new House(getSize());
            columns[index] = new House(getSize());
            blocks[index] = new House(getSize());
        }

        for (int row = 0; row < getSize(); row++) {
            for (int column = 0; column < getSize(); column++) {
                cells[row][column] = new Cell(row, column);
                rows[row].setCell(column, cells[row][column]);
                columns[column].setCell(row, cells[row][column]);
                blocks[blockAt(row, column)].setCell(
                        cellInBlockAt(row, column), cells[row][column]);
            }
        }
    }

    /* Getter and Setter */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(int row, int column, int value) {
        cells[row][column].setValue(value);
    }

    public int getBlockSize() {
        return blockSize;
    }

    protected void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    protected House getRow(int index) {
        return rows[index];
    }

/* Methods */

    /**
     * calculates the index that should be used to identify the block in the
     * blocks array at coordinate (row, column).
     */
    public final int blockAt(int row, int column) {
        return column / blockSize + (blockSize * (row / blockSize));
    }

    /**
     * calculates the index within a block to identify the cell from the blocks
     * cell array at coordinate (row, column).
     */
    protected int cellInBlockAt(int row, int column) {
        return (row % blockSize) + ((column % blockSize) * blockSize);
    }

    public static int blocksPerEdge(int size) {
        return intSqrt(size);
    }

    public static boolean isSquareOfNaturalNumber(int number) {
        return intSqrt(number)* intSqrt(number)== number;
    }

    public static int intSqrt(int number){
        return (int) Math.abs(Math.sqrt(number));
    }

    /**
     * calculates all values that are still valid candidates at the coordinate
     * (row, column).
     *
     * @param row
     * @param column
     * @return is encoded in a BitSet: if BitSet at index 1 is true, the value 1
     *         is a valid candidate.
     */
    public BitSet candidates(int row, int column) {
        BitSet candidates = new BitSet(getSize() + 1);
        candidates.set(1, getSize() + 1, true);
        candidates.and(rows[row].candidates());
        candidates.and(columns[column].candidates());
        candidates.and(blocks[blockAt(row, column)].candidates());
        return candidates;
    }

    /**
     * returns a random selection of the values still possible for a position in the Sudoku puzzle.
     *
     * @param row
     * @param column
     * @return a value that can still legally be set for this position
     */
    public int getCandidate(int row, int column) {
        Random random = new Random();
        int maxindex = this.candidates(row, column).cardinality();
        return candidates(row, column).nextSetBit(random.nextInt(maxindex));
    }

    public void reset() {
        for (int row = 0; row < getSize(); row++) {
            for (int column = 0; column < getSize(); column++) {
                getCell(row,column).reset();
            }
        }
    }

    public void create() {
        reset();
        fillSymmetrically();
        for (int row = 0; row < getSize(); row++) {
            for (int column = 0; column < getSize(); column++) {
                if (getCell(row, column).isSet()) {
                    getCell(row, column).setGiven(true);
                }
            }
        }
    }

    public void fillSymmetrically() {
        for (int i = 0; i < getSize(); i++) {
            Cell cell1 = getRandomCell();
            Cell cell2 = getSymmetricCell(cell1);
            cell1.setValue(getCandidate(cell1.getRow(), cell1.getColumn()));
            cell2.setValue(getCandidate(cell2.getRow(), cell2.getColumn()));
        }

    }

    public Cell getRandomCell() {
        Random random = new Random();
        Cell[] unsetCells = getUnsetCells();

        return unsetCells[random.nextInt(unsetCells.length)];
    }

    Cell getSymmetricCell(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int symmetricRow = (getSize() - 1) - row;
        int symmetricColumn = (getSize() - 1) - column;

        return getCell(symmetricRow, symmetricColumn);
    }

    public Cell[] getUnsetCells() {
        int i = 0;
        Cell[] setCells = new Cell[countUnsetCells()];
        for (int row = 0; row < getSize(); row++) {
            for (int column = 0; column < getSize(); column++) {
                if (getCell(row, column).isUnSet()) {
                    setCells[i] = getCell(row, column);
                    i++;
                }
            }
        }
        return setCells;
    }

    public int countUnsetCells() {
        int count = 0;
        for (int r = 0; r < getSize(); r++) {
            count += getRow(r).countUnsetCells();
        }
        return count;
    }


    /**
     * returns a string of the form +---+ (i.e. in the case of blockSize = 1)
     */
    public String blockSeparator(int blockSize) {
        StringBuilder result = new StringBuilder("+");
        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize * 2 + 1; j++) {
                result.append("-");
            }
            result.append("+");
        }
        return result.toString();
    }

    /**
     * returns a String of the form (i.e for size = 1) +---+ | | +---+
     */
    @Override
    public String toString() {
        return toString(" ");
    }

    public String toString(String zero) {
        String newLine = System.getProperty("line.separator");
        String result = blockSeparator(blockSize) + newLine;
        for (int row = 0; row < getSize(); row++) {
            result = result + getRow(row).toString(zero) + newLine;
            if ((row + 1) % blockSize == 0) {
                result = result + blockSeparator(blockSize) + newLine;
            }

        }
        return result;
    }
}
