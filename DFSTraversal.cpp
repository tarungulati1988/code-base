#include <cstdlib>
#include <iostream>

using namespace std;

class Node{
           int key;
           Node* left;
           Node* right;
           public:
                  Node(){
                         key = -1;
                         left = NULL;
                         right = NULL;    
                  };
                  void setKey(int akey){
                                  key = akey;
                       };
                  void setLeft(Node* aleft){
                                     left = aleft;
                       };
                  void setRight(Node* aright){
                                      right = aright;
                       };
                  int Key(){
                            return key;
                      };
                  Node* Left(){
                               return left;
                        };
                  Node* Right(){
                                return right;
                        };
      };
      

class Tree{
           Node* root;
           public:
                  Tree();
                  ~Tree();
                  Node* Root(){
                               return root;
                        };
                  void addNode(int key);
                  void inOrder(Node* n);
                  void preOrder(Node* n);
                  void postOrder(Node* n);
           private:
                   void addNode(int key, Node* leaf);
                   void freeNode(Node* leaf);
      };
      

Tree::Tree(){
                   root = NULL;
             }
             
Tree::~Tree(){
                   freeNode(root);
              }

void Tree::freeNode(Node* leaf){
                         if(leaf != NULL){
                                    freeNode(leaf->Left());
                                    freeNode(leaf->Right());
                                    delete leaf;
                                 }
     }
     
void Tree::addNode(int key){
                       if(root == NULL){
                                  cout<<"Add root node: "<<key<<endl;
                                  Node* n = new Node();
                                  n->setKey(key);
                                  root = n;
                               }
                       else{
                               cout<<"Adding another node: "<<key<<endl;
                               addNode(key, root);
                            }
     }
     
void Tree::addNode(int key, Node* leaf){
                      if(key <= leaf->Key()){
                             if(leaf->Left() != NULL)
                             addNode(key, leaf->Left());
                             else{
                                          Node* n = new Node();
                                          n->setKey(key);
                                          leaf->setLeft(n);
                                  }
                             }
                      else{
                             if(leaf->Right() != NULL)
                             addNode(key, leaf->Right());
                             else{
                                          Node* n = new Node();
                                          n->setKey(key);
                                          leaf->setRight(n);
                                  }
                           }
     }
     
void Tree::inOrder(Node* n){
                         if(n){
                                  inOrder(n->Left());
                                  cout<<n->Key()<<"   ";
                                  inOrder(n->Right());
                               }
     }
     
void Tree::preOrder(Node* n){
                          if(n){
                                  cout<<n->Key()<<"   ";
                                  preOrder(n->Left());
                                  preOrder(n->Right());
                                }
     }
     
void Tree::postOrder(Node* n){
                           if(n){
                                  postOrder(n->Left());
                                  postOrder(n->Right());
                                  cout<<n->Key()<<"    ";
                                 }
     }
     


void main(void)
{
   Tree* tree = new Tree();
   tree->addNode(30);
   tree->addNode(10);
   tree->addNode(20);
   tree->addNode(40);
   tree->addNode(50);
   tree->addNode(5);

   cout << "In order traversal" << endl;
   tree->inOrder(tree->Root());
   cout << endl;

   cout << "Pre order traversal" << endl;
   tree->preOrder(tree->Root());
   cout << endl;

   cout << "Post order traversal" << endl;
   tree->postOrder(tree->Root());
   cout << endl;

   delete tree;
   getchar();
}
