import { create } from "zustand";

interface ModelState {
  isPasswordModalOpen: boolean;
  setPasswordModalOpen: (isOpen: boolean) => void;
}

const useModelStore = create<ModelState>((set) => ({
  isPasswordModalOpen: false,
  setPasswordModalOpen: (isOpen) => set({ isPasswordModalOpen: isOpen }),
})); //모달창 처리 완료

interface MoimReviewModelState {
  isReviewModalOpen: boolean;
  setReviewModalOpen: (isOpen: boolean) => void;
}

const useMoimReviewStore = create<MoimReviewModelState>((set) => ({
  isReviewModalOpen: false,
  setReviewModalOpen: (isOpen) => set({ isReviewModalOpen: isOpen }),
})); //모달창 처리 완료

interface MoimCountData {
  Moimcount: number;
  setMoimCount: (MoimCount: number) => void;
}
const useMoimCountData = create<MoimCountData>((set) => ({
  Moimcount: 0,
  setMoimCount: (MoimCounts) => set({ Moimcount: MoimCounts }),
})); //현재 가입 중인 모임의 수

interface LoginState {
  Login: {
    id: string;
    password: string;
  };
  setPassword: (password: string) => void;
  setId: (id: string) => void;
}

const useLoginStore = create<LoginState>((set) => ({
  Login: {
    id: "",
    password: "",
  },
  setPassword: (password) =>
    set((state) => ({
      Login: {
        ...state.Login,
        password,
      },
    })),
  setId: (id) =>
    set((state) => ({
      Login: {
        ...state.Login,
        id,
      },
    })),
})); //처리 완료

interface UserData {
  email: string;
  password: string;
  confirmPassword: string;
  nickname: string;
  profileImage: File | null;
  gender: string;
  birthdate: {
    year: string;
    month: string;
    day: string;
  };
  address: {
    basic: string;
    detail: string;
  };
  introduction: string;
  checked: boolean;
  interests: string;
}

interface UserState {
  setAddressDetail(value: string): void;
  userData: UserData;
  setEmail: (email: string) => void;
  setPassword: (password: string) => void;
  setConfirmPassword: (confirmPassword: string) => void;
  setNickname: (nickname: string) => void;
  setProfileImage: (profileImage: File | null) => void;
  setGender: (gender: string) => void;
  setBirthdate: (year: string, month: string, day: string) => void;
  setAddress: (basic: string, detail: string) => void;
  setIntroduction: (introduction: string) => void;
  setChecked: (checked: boolean) => void;
  setInterests: (Interests: string) => void;
  resetMoimData: () => void; // MoimData 초기화
}

const useUserStore = create<UserState>((set) => ({
  userData: {
    email: "",
    password: "",
    confirmPassword: "",
    nickname: "",
    profileImage: null,
    gender: "",
    birthdate: {
      year: "",
      month: "",
      day: "",
    },
    address: {
      basic: "",
      detail: "",
    },
    introduction: "",
    checked: false,
    interests: "",
  },
  setAddressDetail: (value: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        addressDetail: value,
      },
    })),
  setInterests: (interests: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        interests,
      },
    })),
  setEmail: (email: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        email,
      },
    })),
  setPassword: (password: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        password,
      },
    })),
  setConfirmPassword: (confirmPassword: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        confirmPassword,
      },
    })),
  setNickname: (nickname: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        nickname,
      },
    })),
  setProfileImage: (profileImage: File | null) =>
    set((state) => ({
      userData: {
        ...state.userData,
        profileImage,
      },
    })),
  setGender: (gender: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        gender,
      },
    })),
  setBirthdate: (year: string, month: string, day: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        birthdate: { year, month, day },
      },
    })),
  setAddress: (basic: string, detail: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        address: { basic, detail },
      },
    })),
  setIntroduction: (introduction: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        introduction,
      },
    })),
  setChecked: (checked: boolean) =>
    set((state) => ({
      userData: {
        ...state.userData,
        checked,
      },
    })),
  resetMoimData: () => {
    set(() => ({
      userData: {
        email: "",
        password: "",
        confirmPassword: "",
        nickname: "",
        profileImage: null,
        gender: "",
        birthdate: {
          year: "",
          month: "",
          day: "",
        },
        address: {
          basic: "",
          detail: "",
        },
        introduction: "",
        checked: false,
        interests: "",
      },
    }));
  },
}));

//MoimData
export interface MoimData {
  moimId?: string;
  title: string;
  isOnline: boolean;
  maxpeople: number;
  organizer: string;
  expirationDate: Date;
  evenDate: Date;
  image: string;
  description: string;
  tag: string[];
  location: string;
  category: string;
  categoryDetail: string;
}
export interface MoimDetailStore {
  moimdetailId: number;
  moimId: string;
  content: string;
  minPeople: number;
  Pay: number;
  Members: string[];
  Approval: boolean; //승인 필요
}

export interface MoimDataStore {
  moimData: MoimData;
  moimDetail: MoimDetailStore;
  setimage: (image: string) => void;
  setTitle: (title: string) => void;
  setMaxpeople: (maxpeople: number) => void;
  setMembers: (Members: string) => void;
  setDescription: (description: string) => void;
  setTag: (index: number, value: string) => void;
  setMoimData: (moimData: MoimData) => void; // 전체 MoimData 설정
  resetMoimData: () => void; // MoimData 초기화
  setContent: (content: string) => void;
  setMinPeople: (minPeople: number) => void;
  setPay: (Pay: number) => void;
  setisOnline: (isOnline: boolean) => void;
  setOrganizer: (organizer: string) => void;
  setDate: (expirationDate: Date, evenDate: Date) => void;
  setLocation: (location: string) => void;
  setcategory: (category: string) => void;
  setcategoryDetail: (categoryDetail: string) => void;
}

const useMoimStore = create<{ MoimDataStore: MoimDataStore }>((set) => ({
  MoimDataStore: {
    moimData: {
      image: "",
      title: "",
      maxpeople: 0,
      description: "",
      tag: [],
      isOnline: false,
      organizer: "",
      expirationDate: new Date(),
      evenDate: new Date(),
      location: "",
      category: "",
      categoryDetail: "",
    },
    moimDetail: {
      moimdetailId: 0,
      moimId: "",
      content: "",
      Members: [],
      minPeople: 0,
      Pay: 0,
      Approval: false,
    },
    setimage: (image: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, image },
        },
      })),
    setTitle: (title: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, title },
        },
      })),
    setMaxpeople: (maxpeople: number) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, maxpeople },
        },
      })),
    setDescription: (description: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, description },
        },
      })),
    setisOnline: (isOnline: boolean) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, isOnline },
        },
      })),
    setOrganizer: (organizer: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, organizer },
        },
      })),
    setDate: (expirationDate: Date, evenDate: Date) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: {
            ...state.MoimDataStore.moimData,
            expirationDate,
            evenDate,
          },
        },
      })),
    setLocation: (location: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: {
            ...state.MoimDataStore.moimData,
            location,
          },
        },
      })),
    setcategory: (category: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: {
            ...state.MoimDataStore.moimData,
            category,
          },
        },
      })),

    setcategoryDetail: (categoryDetail: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: {
            ...state.MoimDataStore.moimData,
            categoryDetail,
          },
        },
      })),
    setTag: (index, value) =>
      set((state) => {
        const newTags = [...state.MoimDataStore.moimData.tag];
        newTags[index] = value;
        return {
          MoimDataStore: {
            ...state.MoimDataStore,
            moimData: {
              ...state.MoimDataStore.moimData,
              tag: newTags,
            },
          },
        };
      }),
    setMoimData: (moimData: MoimData) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData,
        },
      })),
    resetMoimData: () =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: {
            image: "",
            title: "",
            maxpeople: 0,
            description: "",
            tag: [],
            isOnline: false,
            organizer: "",
            expirationDate: new Date(),
            evenDate: new Date(),
            location: "",
            category: "",
            categoryDetail: "",
          },
          moimDetail: {
            moimdetailId: 0,
            moimId: "",
            Members: [],
            content: "",
            minPeople: 0,
            Pay: 0,
            Approval: false,
          },
        },
      })),
    setMembers: (Members: string) =>
      set((state) => {
        const newMembers = [...state.MoimDataStore.moimDetail.Members, Members];
        return {
          MoimDataStore: {
            ...state.MoimDataStore,
            moimDetail: {
              ...state.MoimDataStore.moimDetail,
              Members: newMembers,
            },
          },
        };
      }),
    setContent: (content: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimDetail: { ...state.MoimDataStore.moimDetail, content },
        },
      })),
    setMinPeople: (minPeople: number) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimDetail: { ...state.MoimDataStore.moimDetail, minPeople },
        },
      })),
    setPay: (Pay: number) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimDetail: { ...state.MoimDataStore.moimDetail, Pay },
        },
      })),
    setApproval: (Approval: boolean) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimDetail: { ...state.MoimDataStore.moimDetail, Approval },
        },
      })),
  },
})); //모임 데이터 처리 완료

export {
  useModelStore,
  useLoginStore,
  useUserStore,
  useMoimStore,
  useMoimCountData,
  useMoimReviewStore,
};
