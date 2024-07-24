class AVLTreeNode {
    Movie movie;
    int height;
    AVLTreeNode left, right;

    public AVLTreeNode(Movie movie) {
        this.movie = movie;
        this.height = 1;
    }
}
