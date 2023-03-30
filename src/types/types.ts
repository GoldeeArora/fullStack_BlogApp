
export interface SignupForm {
    name: string;
    email: string;
    password: string;
    about: string;
  }
  export interface User {
    id: number|string;
    name: string;
    email: string;
    password: string;
    about: string;
  }
  export interface Category {
    categoryid?: number|string;
    categoryTitle?: string;
    categoryDescription?: string;
  }
  
  export interface PostType{
    postId: number|string;
    title: string;
    content: string;
    imageName: string;
    addedDate: Date;
    category: Category;
    user: User;
  }

  export interface CategoryType{
    categoryId ?: string | number;
    categorytitle: string;
    categoryDescription: string;
  }