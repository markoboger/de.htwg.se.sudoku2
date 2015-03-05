package de.htwg.sudoku.model;

/**
 * The Grid is the playing field of a Sudoku puzzle.
 * It consists of Cells.
 * Cells are organized in Houses.
 * The Grid has a size, which is the number of cells in a row or column. Size must be 1, 4, or 9.
 */
public class Grid {

/* Fields */
    private static final int MAXSIZE = 9;
    private final static double EPSILON = 0.00001;

    private int size;
    private int blockSize;


    private Cell[][] cells;
    private House[] rows;
    private House[] columns;
    private House[] blocks;

/* Constructors */
    public Grid(int size) {
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

    public House[] getRows() {
        return rows;
    }

    public void setRows(House[] rows) {
        this.rows = rows;
    }

    public House[] getColumns() {
        return columns;
    }

    public void setColumns(House[] columns) {
        this.columns = columns;
    }

    public House[] getBlocks() {
        return blocks;
    }

    public void setBlocks(House[] blocks) {
        this.blocks = blocks;
    }

    public int getBlockSize() {
        return blockSize;
    }

    protected void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
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
        return ((row % blockSize) + ((column % blockSize) * blockSize));
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

}
