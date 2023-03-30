import { View, Text, FlatList } from 'react-native'
import React, { useState,useEffect } from 'react'
import { useRoute } from '@react-navigation/native'
//@ts-ignore
import {API_URL} from "@env"
import { useAppSelector } from '../../redux/features/hooks';
import { selectToken } from '../../redux/features/tokenSlice';
import {PostType} from "../../types/types"
import axios from 'axios';
import Post from '../../components/Post';
export default function PostsByCategory():JSX.Element {
    const [posts,setPosts] = useState<PostType[]>();
    const route = useRoute();
    const getToken = useAppSelector(selectToken);
    const params = route.params as {id: string|number};
const categoryId = typeof params.id!=='string' ? params.id.toString() : params.id;
    const getAllPosts = ()=>{
        axios.get(`${API_URL}/api/category/${categoryId}/posts`,{
          headers: {
            Authorization: `Bearer ${getToken.token}`
          }
        }).then(res=>
          {
            console.log(res.data)
            setPosts(res.data);
          }).catch(e=>console.log(e));
      }
      useEffect(()=>{
getAllPosts();
      },[])
  return (
    <View>
      <FlatList 
    data={posts}
    renderItem={({item}: {item: PostType}):JSX.Element=> 
    (
      <View>
    <Post id={item.postId} imageSource={item.imageName} date={item.addedDate} title={item.title} content={item.content}/>
        </View>
    )
  }
  keyExtractor={(item:PostType,index:number):string=>index.toString()}
  showsHorizontalScrollIndicator={false}
  showsVerticalScrollIndicator={false}

  />
    </View>
  )
}