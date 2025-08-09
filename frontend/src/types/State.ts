import { create } from "zustand";

interface ModelState {
  isPasswordModalOpen: boolean;
  setPasswordModalOpen: (isOpen: boolean) => void;
}

const useModelStore = create<ModelState>((set) => ({
  isPasswordModalOpen: false,
  setPasswordModalOpen: (isOpen) => set({ isPasswordModalOpen: isOpen }),
})); //모달창 처리 완료

interface MoimCountData {
  Moimcount: number;
  setMoimCount: (MoimCount: number) => void;
}
const useMoimCountData = create<MoimCountData>((set) => ({
  Moimcount: 0,
  setMoimCount: (MoimCounts) => set({ Moimcount: MoimCounts }),
}));

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
  addressDetail: string | number | readonly string[] | undefined;
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
    addressDetail: undefined,
  },
  setAddressDetail: (value: string) =>
    set((state) => ({
      userData: {
        ...state.userData,
        addressDetail: value,
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
}));

//MoimData
export interface MoimData {
  id?: number;
  image: string;
  title: string;
  maxpeople: number;
  Members: string[];
  description: string;
  tag: string[];
  Join: boolean;
}
export interface MoimDetailStore {
  moimdetailId: number;
  moimId: string;
  content: string;
  minPeople: number;
  Pay: number;
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
  setJoin: (Join: boolean) => void;
}

const useMoimStore = create<{ MoimDataStore: MoimDataStore }>((set) => ({
  MoimDataStore: {
    moimData: {
      image: "",
      title: "",
      maxpeople: 0,
      Members: [],
      description: "",
      tag: [],
      Join: false,
    },
    moimDetail: {
      moimdetailId: 0,
      moimId: "",
      content: "",
      minPeople: 0,
      Pay: 0,
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
    setMembers: (Members: string) =>
      set((state) => {
        const newMembers = [...state.MoimDataStore.moimData.Members, Members];
        return {
          MoimDataStore: {
            ...state.MoimDataStore,
            moimData: {
              ...state.MoimDataStore.moimData,
              Members: newMembers,
            },
          },
        };
      }),
    setDescription: (description: string) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, description },
        },
      })),
    setJoin: (Join: boolean) =>
      set((state) => ({
        MoimDataStore: {
          ...state.MoimDataStore,
          moimData: { ...state.MoimDataStore.moimData, Join },
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
            Members: [],
            description: "",
            tag: [],
            Join: false,
          },
          moimDetail: {
            moimdetailId: 0,
            moimId: "",
            content: "",
            minPeople: 0,
            Pay: 0,
          },
        },
      })),
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
  },
})); //모임 데이터 처리 완료

export {
  useModelStore,
  useLoginStore,
  useUserStore,
  useMoimStore,
  useMoimCountData,
};
